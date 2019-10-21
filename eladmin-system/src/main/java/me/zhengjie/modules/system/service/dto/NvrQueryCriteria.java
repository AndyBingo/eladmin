package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
* @author andy
* @date 2019-10-17
*/
@Data
public class NvrQueryCriteria{

    // 模糊
    @Query
    private String name;

    // 精确
    @Query
    private String ipAddr;
}