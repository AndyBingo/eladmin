package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author andy
* @date 2019-10-17
*/
@Data
public class DeviceDTO implements Serializable {

    private Long id;

    private Timestamp createTime;

    // 设备名称
    private String deviceName;

    // 流地址
    private String streamAddr;

    // IP地址
    private String ipAddr;

    private Long nvrId;
}