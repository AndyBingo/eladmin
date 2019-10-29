package cn.mine.modules.system.service.dto;

import lombok.Data;
import cn.mine.annotation.Query;

/**
 * 公共查询类
 */
@Data
public class DictQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,remark")
    private String blurry;
}
