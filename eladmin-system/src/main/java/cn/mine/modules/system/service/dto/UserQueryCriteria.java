package cn.mine.modules.system.service.dto;

import lombok.Data;
import cn.mine.annotation.Query;
import java.io.Serializable;
import java.util.Set;

/**
 * @author andy
 * @date 2018-11-23
 */
@Data
public class UserQueryCriteria implements Serializable {

    @Query
    private Long id;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds;

    @Query
    private String username;

    @Query
    private String nickname;

    @Query
    private Boolean enabled;

    private Long deptId;
}
