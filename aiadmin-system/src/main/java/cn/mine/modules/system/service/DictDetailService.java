package cn.mine.modules.system.service;

import cn.mine.modules.system.domain.DictDetail;
import cn.mine.modules.system.service.dto.DictDetailDTO;
import cn.mine.modules.system.service.dto.DictDetailQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
* @author andy
* @date 2019-04-10
*/
@CacheConfig(cacheNames = "dictDetail")
public interface DictDetailService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    DictDetailDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    DictDetailDTO create(DictDetail resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(DictDetail resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    @Cacheable(keyGenerator = "keyGenerator")
    Map queryAll(DictDetailQueryCriteria criteria, Pageable pageable);
}