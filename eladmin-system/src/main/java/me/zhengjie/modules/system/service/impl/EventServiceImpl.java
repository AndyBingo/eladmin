package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.quartz.domain.QuartzJob;
import me.zhengjie.modules.quartz.service.QuartzJobService;
import me.zhengjie.modules.system.domain.Event;
import me.zhengjie.modules.system.service.AlgorithmService;
import me.zhengjie.modules.system.service.DeviceService;
import me.zhengjie.modules.system.service.dto.AlgorithmDTO;
import me.zhengjie.modules.system.service.dto.DeviceDTO;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.system.repository.EventRepository;
import me.zhengjie.modules.system.service.EventService;
import me.zhengjie.modules.system.service.dto.EventDTO;
import me.zhengjie.modules.system.service.dto.EventQueryCriteria;
import me.zhengjie.modules.system.service.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

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
        return eventMapper.toDto(eventRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Event resources) {
        Optional<Event> optionalEvent = eventRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalEvent,"Event","id",resources.getId());
        Event event = optionalEvent.get();
        event.setIsClosed(resources.getIsClosed());
        eventRepository.save(event);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void manageEventAlarmJob(Event resources){
        Long algorithmId = resources.getAlgorithm().getId();
        Long deviceId = resources.getDevice().getId();
        AlgorithmDTO algorithmDTO = algorithmService.findById(algorithmId);
        String exception = algorithmDTO.getException();
        DeviceDTO deviceDTO = deviceService.findById(deviceId);

        int count = eventRepository.countEventsByDeviceIdAndAlgorithmIdAndIsClosed(deviceId,algorithmId);
        if (count > 0) {
            String jobName = deviceDTO.getDeviceName()+algorithmDTO.getName();
            QuartzJob quartzJob = quartzJobService.findByJobName(jobName);
            if (quartzJob == null) {
                quartzJob = new QuartzJob();
                quartzJob.setIntervalSec(algorithmDTO.getAlarmPolicy().getAlarmInterval());
                quartzJob.setJobName(jobName);
                quartzJobService.create(quartzJob);
            }
        }else if (count == 0){

        }



    }
}