package cn.mine.modules.system.service.impl;

import cn.mine.modules.system.domain.Device;
import cn.mine.utils.ValidationUtil;
import cn.mine.modules.system.repository.DeviceRepository;
import cn.mine.modules.system.service.DeviceService;
import cn.mine.modules.system.service.dto.DeviceDTO;
import cn.mine.modules.system.service.dto.DeviceQueryCriteria;
import cn.mine.modules.system.service.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.mine.utils.PageUtil;
import cn.mine.utils.QueryHelp;

/**
* @author andy
* @date 2019-10-17
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public Object queryAll(DeviceQueryCriteria criteria, Pageable pageable){
        Page<Device> page = deviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(deviceMapper::toDto));
    }

    @Override
    public Object queryAll(DeviceQueryCriteria criteria){
        return deviceMapper.toDto(deviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DeviceDTO findById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        ValidationUtil.isNull(device,"Device","id",id);
        return deviceMapper.toDto(device.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeviceDTO create(Device resources) {
        return deviceMapper.toDto(deviceRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Device resources) {
        Optional<Device> optionalDevice = deviceRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalDevice,"Device","id",resources.getId());
        Device device = optionalDevice.get();
        device.setAlgorithms(resources.getAlgorithms());
        device.setDeviceName(resources.getDeviceName());
        device.setIpAddr(resources.getIpAddr());
        device.setNvr(resources.getNvr());
        device.setStreamAddr(resources.getStreamAddr());
        deviceRepository.save(device);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }
}