package cn.mine.repository;

import cn.mine.domain.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author andy
 * @date 2018-12-31
 */
public interface AlipayRepository extends JpaRepository<AlipayConfig,Long> {
}
