package cn.mine.modules.system.service.dto;

import lombok.Data;
import cn.mine.annotation.Query;
import java.util.Set;

/**
* @author andy
* @date 2019-03-25
*/
@Data
public class DeptQueryCriteria{

    @Query(type = Query.Type.IN, propName="id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private Long pid;
}