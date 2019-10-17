package me.zhengjie.modules.system.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.system.domain.Nvr;
import me.zhengjie.modules.system.service.dto.NvrDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author andy
* @date 2019-10-17
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NvrMapper extends EntityMapper<NvrDTO, Nvr> {

}