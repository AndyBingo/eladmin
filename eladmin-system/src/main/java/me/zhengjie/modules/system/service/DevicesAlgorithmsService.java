package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.DevicesAlgorithms;
import me.zhengjie.modules.system.service.dto.DevicesAlgorithmsDTO;
import me.zhengjie.modules.system.service.dto.DevicesAlgorithmsQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author andy
* @date 2019-10-07
*/
//@CacheConfig(cacheNames = "devicesAlgorithms")
public interface DevicesAlgorithmsService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(DevicesAlgorithmsQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(DevicesAlgorithmsQueryCriteria criteria);

    /**
     * findById
     * @param algorithmId
     * @return
     */
    //@Cacheable(key = "#p0")
    DevicesAlgorithmsDTO findById(Long algorithmId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    DevicesAlgorithmsDTO create(DevicesAlgorithms resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(DevicesAlgorithms resources);

    /**
     * delete
     * @param algorithmId
     */
    //@CacheEvict(allEntries = true)
    void delete(Long algorithmId);
}