package cn.mine.modules.system.service.mapper;

import cn.mine.mapper.EntityMapper;
import cn.mine.modules.system.domain.Event;
import cn.mine.modules.system.service.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author andy
* @date 2019-10-17
*/
@Mapper(componentModel = "spring",uses = {DeviceMapper.class, AlgorithmMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper extends EntityMapper<EventDTO, Event> {

}