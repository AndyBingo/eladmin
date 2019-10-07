package me.zhengjie.modules.system.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.system.domain.DevicesAlgorithms;
import me.zhengjie.modules.system.service.dto.DevicesAlgorithmsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author andy
* @date 2019-10-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DevicesAlgorithmsMapper extends EntityMapper<DevicesAlgorithmsDTO, DevicesAlgorithms> {

}