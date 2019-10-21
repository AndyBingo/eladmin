package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.Event;
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
    @Transactional(rollbackFor = Exception.class)
    public EventDTO create(Event resources) {
        resources.setStatus("未关闭");
        return eventMapper.toDto(eventRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Event resources) {
        Optional<Event> optionalEvent = eventRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalEvent,"Event","id",resources.getId());
        Event event = optionalEvent.get();
        event.setStatus(resources.getStatus());
        eventRepository.save(event);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}