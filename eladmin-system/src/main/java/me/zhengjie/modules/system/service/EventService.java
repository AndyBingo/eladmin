package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.Event;
import me.zhengjie.modules.system.service.dto.EventDTO;
import me.zhengjie.modules.system.service.dto.EventQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;

/**
* @author andy
* @date 2019-10-17
*/
//@CacheConfig(cacheNames = "event")
public interface EventService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(EventQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(EventQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    EventDTO findById(Long id);

    /**
     *
     * @param start
     * @param end
     * @return
     */
    List<Map<String, Object>> queryAllByStartTimeAndEndTimeGroupByException(String start, String end);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    EventDTO create(Event resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Event resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);

    @Async
    void manageEventAlarmJob(Event resources);
}