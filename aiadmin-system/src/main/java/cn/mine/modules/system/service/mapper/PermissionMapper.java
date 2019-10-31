package cn.mine.modules.system.service.mapper;

import cn.mine.modules.system.domain.Permission;
import cn.mine.mapper.EntityMapper;
import cn.mine.modules.system.service.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author andy
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper extends EntityMapper<PermissionDTO, Permission> {

}
