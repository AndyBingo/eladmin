package cn.mine.modules.system.service.dto;

import lombok.Data;
import cn.mine.annotation.Query;

/**
* @author andy
* @date 2019-04-10
*/
@Data
public class DictDetailQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @Query(propName = "name",joinName = "dict")
    private String dictName;
}