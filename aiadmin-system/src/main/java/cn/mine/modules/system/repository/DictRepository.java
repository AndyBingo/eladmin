package cn.mine.modules.system.repository;

import cn.mine.modules.system.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author andy
* @date 2019-04-10
*/
public interface DictRepository extends JpaRepository<Dict, Long>, JpaSpecificationExecutor {
}