package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import me.zhengjie.annotation.Query;

/**
* @author andy
* @date 2019-10-17
*/
@Data
public class AlarmPolicyQueryCriteria{

    // 精确
    @Query
    private String name;
}