package cn.mine.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.mine.annotation.Query;

import java.util.Set;

/**
* @author andy
* @date 2019-6-4 14:49:34
*/
@Data
@NoArgsConstructor
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query(propName = "id", joinName = "dept")
    private Long deptId;

    @Query(propName = "id", joinName = "dept", type = Query.Type.IN)
    private Set<Long> deptIds;
}