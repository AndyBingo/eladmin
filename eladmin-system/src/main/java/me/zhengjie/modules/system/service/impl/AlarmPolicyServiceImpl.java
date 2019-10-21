package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.AlarmPolicy;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.system.repository.AlarmPolicyRepository;
import me.zhengjie.modules.system.service.AlarmPolicyService;
import me.zhengjie.modules.system.service.dto.AlarmPolicyDTO;
import me.zhengjie.modules.system.service.dto.AlarmPolicyQueryCriteria;
import me.zhengjie.modules.system.service.mapper.AlarmPolicyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

/**
* @author andy
* @date 2019-10-17
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AlarmPolicyServiceImpl implements AlarmPolicyService {

    @Autowired
    private AlarmPolicyRepository alarmPolicyRepository;

    @Autowired
    private AlarmPolicyMapper alarmPolicyMapper;

    @Override
    public Object queryAll(AlarmPolicyQueryCriteria criteria, Pageable pageable){
        Page<AlarmPolicy> page = alarmPolicyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(alarmPolicyMapper::toDto));
    }

    @Override
    public Object queryAll(AlarmPolicyQueryCriteria criteria){
        return alarmPolicyMapper.toDto(alarmPolicyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public AlarmPolicyDTO findById(Long id) {
        Optional<AlarmPolicy> alarmPolicy = alarmPolicyRepository.findById(id);
        ValidationUtil.isNull(alarmPolicy,"AlarmPolicy","id",id);
        return alarmPolicyMapper.toDto(alarmPolicy.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AlarmPolicyDTO create(AlarmPolicy resources) {
        return alarmPolicyMapper.toDto(alarmPolicyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AlarmPolicy resources) {
        Optional<AlarmPolicy> optionalAlarmPolicy = alarmPolicyRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAlarmPolicy,"AlarmPolicy","id",resources.getId());
        AlarmPolicy alarmPolicy = optionalAlarmPolicy.get();
        alarmPolicy.setName(resources.getName());
        alarmPolicy.setAlarmManner(resources.getAlarmManner());
        alarmPolicy.setAlarmInterval(resources.getAlarmInterval());
        alarmPolicy.setAlarmManner(resources.getAlarmManner());
        alarmPolicy.setThreshold(resources.getThreshold());
        alarmPolicy.setUpgradeAlarmManner(resources.getUpgradeAlarmManner());
        alarmPolicyRepository.save(alarmPolicy);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        alarmPolicyRepository.deleteById(id);
    }
}