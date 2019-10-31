package cn.mine.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import cn.mine.annotation.Query;

/**
* @author andy
* @date 2019-10-17
*/
@Data
public class EventQueryCriteria{

    // 精确
    @Query
    private Timestamp startTime;

    // 精确
    @Query
    private Timestamp endTime;

    // 精确
    @Query
    private Long algorithmId;

    // 精确
    @Query
    private Long deviceId;

    @Query
    private Boolean isClosed;
}