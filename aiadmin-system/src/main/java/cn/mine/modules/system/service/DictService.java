package cn.mine.modules.system.service;

import cn.mine.modules.system.domain.Dict;
import cn.mine.modules.system.service.dto.DictDTO;
import cn.mine.modules.system.service.dto.DictQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author andy
* @date 2019-04-10
*/
@CacheConfig(cacheNames = "dict")
public interface DictService {

    /**
     * 查询
     * @param dict
     * @param pageable
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(DictQueryCriteria dict, Pageable pageable);

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    DictDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    DictDTO create(Dict resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Dict resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}