package cn.mine.modules.system.repository;

import cn.mine.modules.system.domain.Nvr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author andy
* @date 2019-10-17
*/
public interface NvrRepository extends JpaRepository<Nvr, Long>, JpaSpecificationExecutor {
}