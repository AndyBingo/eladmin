package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author andy
* @date 2019-10-17
*/
@Data
public class EventDTO implements Serializable {

    private Long id;

    // 开始时间
    private Timestamp startTime;

    // 结束时间
    private Timestamp endTime;

    // 创建时间
    private Timestamp createTime;

    // 异常类型
    private String exception;

    // 设备ID
    private Long deviceId;

    private DeviceDTO device;

    // 状态
    private String status;

    private String image;

    private String video;
}