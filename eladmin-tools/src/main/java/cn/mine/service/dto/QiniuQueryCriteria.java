package cn.mine.service.dto;

import lombok.Data;
import cn.mine.annotation.Query;

/**
 * @author andy
 * @date 2019-6-4 09:54:37
 */
@Data
public class QiniuQueryCriteria{

    @Query(type = Query.Type.INNER_LIKE)
    private String key;
}
