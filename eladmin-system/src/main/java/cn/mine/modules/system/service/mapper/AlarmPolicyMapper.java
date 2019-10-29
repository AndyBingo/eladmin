package cn.mine.modules.system.service.mapper;

import cn.mine.mapper.EntityMapper;
import cn.mine.modules.system.domain.AlarmPolicy;
import cn.mine.modules.system.service.dto.AlarmPolicyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author andy
* @date 2019-10-17
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlarmPolicyMapper extends EntityMapper<AlarmPolicyDTO, AlarmPolicy> {

}