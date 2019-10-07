package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.DevicesAlgorithms;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.system.repository.DevicesAlgorithmsRepository;
import me.zhengjie.modules.system.service.DevicesAlgorithmsService;
import me.zhengjie.modules.system.service.dto.DevicesAlgorithmsDTO;
import me.zhengjie.modules.system.service.dto.DevicesAlgorithmsQueryCriteria;
import me.zhengjie.modules.system.service.mapper.DevicesAlgorithmsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

/**
* @author andy
* @date 2019-10-07
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DevicesAlgorithmsServiceImpl implements DevicesAlgorithmsService {

    @Autowired
    private DevicesAlgorithmsRepository devicesAlgorithmsRepository;

    @Autowired
    private DevicesAlgorithmsMapper devicesAlgorithmsMapper;

    @Override
    public Object queryAll(DevicesAlgorithmsQueryCriteria criteria, Pageable pageable){
        Page<DevicesAlgorithms> page = devicesAlgorithmsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(devicesAlgorithmsMapper::toDto));
    }

    @Override
    public Object queryAll(DevicesAlgorithmsQueryCriteria criteria){
        return devicesAlgorithmsMapper.toDto(devicesAlgorithmsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DevicesAlgorithmsDTO findById(Long algorithmId) {
        Optional<DevicesAlgorithms> devicesAlgorithms = devicesAlgorithmsRepository.findById(algorithmId);
        ValidationUtil.isNull(devicesAlgorithms,"DevicesAlgorithms","algorithmId",algorithmId);
        return devicesAlgorithmsMapper.toDto(devicesAlgorithms.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DevicesAlgorithmsDTO create(DevicesAlgorithms resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setAlgorithmId(snowflake.nextId()); 
        return devicesAlgorithmsMapper.toDto(devicesAlgorithmsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DevicesAlgorithms resources) {
        Optional<DevicesAlgorithms> optionalDevicesAlgorithms = devicesAlgorithmsRepository.findById(resources.getAlgorithmId());
        ValidationUtil.isNull( optionalDevicesAlgorithms,"DevicesAlgorithms","id",resources.getAlgorithmId());
        DevicesAlgorithms devicesAlgorithms = optionalDevicesAlgorithms.get();
        devicesAlgorithms.copy(resources);
        devicesAlgorithmsRepository.save(devicesAlgorithms);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long algorithmId) {
        devicesAlgorithmsRepository.deleteById(algorithmId);
    }
}