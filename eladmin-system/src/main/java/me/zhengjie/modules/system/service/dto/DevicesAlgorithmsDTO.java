package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


/**
* @author andy
* @date 2019-10-07
*/
@Data
public class DevicesAlgorithmsDTO implements Serializable {

    // 设备ID
    // 处理精度丢失问题
    @JsonSerialize(using= ToStringSerializer.class)
    private Long deviceId;

    // 算法ID
    // 处理精度丢失问题
    @JsonSerialize(using= ToStringSerializer.class)
    private Long algorithmId;
}