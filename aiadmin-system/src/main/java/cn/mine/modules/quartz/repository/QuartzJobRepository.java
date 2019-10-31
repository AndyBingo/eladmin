package cn.mine.modules.quartz.repository;

import cn.mine.modules.quartz.domain.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
public interface QuartzJobRepository extends JpaRepository<QuartzJob,Long>, JpaSpecificationExecutor {

    /**
     * 查询启用的任务
     * @return
     */
    List<QuartzJob> findByIsPauseIsFalse();

    @Modifying
    @Query(value = "update quartz_job set count = ?2 where id = ?1",nativeQuery = true)
    void updateQuartzJobCount(Long id, Integer count);

    QuartzJob findByJobName(String jobName);
}
