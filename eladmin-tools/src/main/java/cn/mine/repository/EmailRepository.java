package cn.mine.repository;

import cn.mine.domain.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author andy
 * @date 2018-12-26
 */
public interface EmailRepository extends JpaRepository<EmailConfig,Long> {
}
