package cn.mine.modules.system.service.impl;

import cn.mine.modules.quartz.domain.QuartzJob;
import cn.mine.modules.quartz.service.QuartzJobService;
import cn.mine.modules.system.domain.Event;
import cn.mine.modules.system.service.AlgorithmService;
import cn.mine.modules.system.service.DeviceService;
import cn.mine.modules.system.service.dto.AlgorithmDTO;
import cn.mine.modules.system.service.dto.DeviceDTO;
import cn.mine.utils.ValidationUtil;
import cn.mine.modules.system.repository.EventRepository;
import cn.mine.modules.system.service.EventService;
import cn.mine.modules.system.service.dto.EventDTO;
import cn.mine.modules.system.service.dto.EventQueryCriteria;
import cn.mine.modules.system.service.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.mine.utils.PageUtil;
import cn.mine.utils.QueryHelp;

/**
* @author andy
* @date 2019-10-17
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private QuartzJobService quartzJobService;

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private DeviceService deviceService;

    @Override
    public Object queryAll(EventQueryCriteria criteria, Pageable pageable){
        Page<Event> page = eventRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(eventMapper::toDto));
    }

    @Override
    public Object queryAll(EventQueryCriteria criteria){
        return eventMapper.toDto(eventRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public EventDTO findById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        ValidationUtil.isNull(event,"Event","id",id);
        return eventMapper.toDto(event.get());
    }

    @Override
    public List<Map<String, Object>> queryAllByStartTimeAndEndTimeGroupByException(String start, String end){
        return eventRepository.queryAllByStartTimeAndEndTimeGroupByException(start, end);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EventDTO create(Event resources) {
        resources.setIsClosed(false);
        Event event = eventRepository.save(resources);
        manageEventAlarmJob(event);
        return eventMapper.toDto(event);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Event resources) {
        Optional<Event> optionalEvent = eventRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalEvent,"Event","id",resources.getId());
        Event event = optionalEvent.get();
        event.setIsClosed(resources.getIsClosed());
        eventRepository.save(event);
        manageEventAlarmJob(event);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        EventDTO event = findById(id);
        eventRepository.deleteById(id);
        manageEventAlarmJob(eventMapper.toEntity(event));
    }


    @Override
    public void manageEventAlarmJob(Event resources){
        Long algorithmId = resources.getAlgorithm().getId();
        Long deviceId = resources.getDevice().getId();
        AlgorithmDTO algorithmDTO = algorithmService.findById(algorithmId);
        DeviceDTO deviceDTO = deviceService.findById(deviceId);

        if (algorithmDTO == null || deviceDTO == null){
            return;
        }

        int count = eventRepository.countEventsByDeviceIdAndAlgorithmIdAndIsClosed(deviceId,algorithmId);
        String jobName = deviceDTO.getDeviceName()+algorithmDTO.getName();
        QuartzJob quartzJob = quartzJobService.findByJobName(jobName);
        if (count > 0) {
            if (quartzJob == null) {
                quartzJob = new QuartzJob();
                quartzJob.setIntervalSec(algorithmDTO.getAlarmPolicy().getAlarmInterval());
                quartzJob.setJobName(jobName);
                quartzJob.setParams(String.valueOf(deviceId).concat(",").concat(String.valueOf(algorithmId)));
                quartzJobService.create(quartzJob);
            }
        }else if (count == 0){
            if (quartzJob != null) {
                quartzJobService.delete(quartzJob);
            }
        }
    }
}