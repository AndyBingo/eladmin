package cn.mine.modules.system.service.dto;

import lombok.Data;
import cn.mine.annotation.Query;

/**
 * 公共查询类
 */
@Data
public class PermissionQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,alias")
    private String blurry;
}
