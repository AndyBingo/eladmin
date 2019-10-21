package me.zhengjie.modules.system.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.system.domain.Algorithm;
import me.zhengjie.modules.system.service.dto.AlgorithmDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author andy
* @date 2019-10-17
*/
@Mapper(componentModel = "spring",uses = {AlarmPolicyMapper.class,UserMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlgorithmMapper extends EntityMapper<AlgorithmDTO, Algorithm> {

}