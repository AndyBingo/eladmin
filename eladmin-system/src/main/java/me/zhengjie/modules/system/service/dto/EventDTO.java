package me.zhengjie.modules.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author andy
* @date 2019-10-07
*/
@Data
public class EventDTO implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    private Timestamp createTime;

    private String eventType;

    private Long deviceId;

    private String img;
}