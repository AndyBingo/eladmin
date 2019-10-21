package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.Algorithm;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.system.repository.AlgorithmRepository;
import me.zhengjie.modules.system.service.AlgorithmService;
import me.zhengjie.modules.system.service.dto.AlgorithmDTO;
import me.zhengjie.modules.system.service.dto.AlgorithmQueryCriteria;
import me.zhengjie.modules.system.service.mapper.AlgorithmMapper;
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
public class AlgorithmServiceImpl implements AlgorithmService {

    @Autowired
    private AlgorithmRepository algorithmRepository;

    @Autowired
    private AlgorithmMapper algorithmMapper;

    @Override
    public Object queryAll(AlgorithmQueryCriteria criteria, Pageable pageable){
        Page<Algorithm> page = algorithmRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(algorithmMapper::toDto));
    }

    @Override
    public Object queryAll(AlgorithmQueryCriteria criteria){
        return algorithmMapper.toDto(algorithmRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public AlgorithmDTO findById(Long id) {
        Optional<Algorithm> algorithm = algorithmRepository.findById(id);
        ValidationUtil.isNull(algorithm,"Algorithm","id",id);
        return algorithmMapper.toDto(algorithm.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AlgorithmDTO create(Algorithm resources) {
        return algorithmMapper.toDto(algorithmRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Algorithm resources) {
        Optional<Algorithm> optionalAlgorithm = algorithmRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAlgorithm,"Algorithm","id",resources.getId());
        Algorithm algorithm = optionalAlgorithm.get();
        algorithm.setAlarmPolicy(resources.getAlarmPolicy());
        algorithm.setDevices(resources.getDevices());
        algorithm.setException(resources.getException());
        algorithm.setName(resources.getName());
        algorithm.setParams(resources.getParams());
        algorithm.setServiceUrl(resources.getServiceUrl());
        algorithm.setUsers(resources.getUsers());
        algorithmRepository.save(algorithm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        algorithmRepository.deleteById(id);
    }
}