package cn.mine.modules.quartz.service.impl;

import cn.mine.modules.quartz.domain.QuartzJob;
import cn.mine.modules.quartz.repository.QuartzJobRepository;
import cn.mine.modules.quartz.repository.QuartzLogRepository;
import cn.mine.modules.quartz.service.QuartzJobService;
import cn.mine.modules.quartz.service.dto.JobQueryCriteria;
import cn.mine.modules.quartz.utils.QuartzManage;
import cn.mine.utils.PageUtil;
import cn.mine.utils.QueryHelp;
import cn.mine.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Service(value = "quartzJobService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    private QuartzJobRepository quartzJobRepository;

    @Autowired
    private QuartzLogRepository quartzLogRepository;

    @Autowired
    private QuartzManage quartzManage;

    @Override
    public Object queryAll(JobQueryCriteria criteria, Pageable pageable){
        return PageUtil.toPage(quartzJobRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable));
    }

    @Override
    public Object queryAllLog(JobQueryCriteria criteria, Pageable pageable){
        return PageUtil.toPage(quartzLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable));
    }

    @Override
    public QuartzJob findById(Long id) {
        Optional<QuartzJob> quartzJob = quartzJobRepository.findById(id);
        ValidationUtil.isNull(quartzJob,"QuartzJob","id",id);
        return quartzJob.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuartzJob create(QuartzJob resources) {
        resources = quartzJobRepository.save(resources);
        quartzManage.addJob(resources);
        return resources;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuartzJob resources) {
        resources = quartzJobRepository.save(resources);
        quartzManage.updateJobInterval(resources);
    }

    @Override
    public void updateIsPause(QuartzJob quartzJob) {
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(false);
        } else {
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(true);
        }
        quartzJobRepository.save(quartzJob);
    }

    @Override
    public void execution(QuartzJob quartzJob) {
        quartzManage.runAJobNow(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(QuartzJob quartzJob) {
        quartzManage.deleteJob(quartzJob);
        quartzJobRepository.delete(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void incrementCountOne(QuartzJob quartzJob){
        quartzJobRepository.updateQuartzJobCount(quartzJob.getId(), quartzJob.getCount());
    }

    @Override
    public QuartzJob findByJobName(String jobName){
        return  quartzJobRepository.findByJobName(jobName);
    }
}
