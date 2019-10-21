package me.zhengjie.modules.system.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.system.domain.Device;
import me.zhengjie.modules.system.service.dto.DeviceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author andy
* @date 2019-10-17
*/
@Mapper(componentModel = "spring",uses = {NvrMapper.class,AlgorithmMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeviceMapper extends EntityMapper<DeviceDTO, Device> {

}