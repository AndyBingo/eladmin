package cn.mine.modules.system.service.dto;

import lombok.Data;
import cn.mine.annotation.Query;

/**
* @author andy
* @date 2019-10-17
*/
@Data
public class AlgorithmQueryCriteria{

    // 精确
    @Query
    private String name;

    // 精确
    @Query
    private String exception;
}