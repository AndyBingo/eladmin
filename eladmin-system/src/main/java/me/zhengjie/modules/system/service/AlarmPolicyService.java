package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.AlarmPolicy;
import me.zhengjie.modules.system.service.dto.AlarmPolicyDTO;
import me.zhengjie.modules.system.service.dto.AlarmPolicyQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author andy
* @date 2019-10-17
*/
//@CacheConfig(cacheNames = "alarmPolicy")
public interface AlarmPolicyService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(AlarmPolicyQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AlarmPolicyQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    AlarmPolicyDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    AlarmPolicyDTO create(AlarmPolicy resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(AlarmPolicy resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);
}