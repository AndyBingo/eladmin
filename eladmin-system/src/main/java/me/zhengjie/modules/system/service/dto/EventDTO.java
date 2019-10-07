package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author andy
* @date 2019-10-07
*/
@Data
public class EventDTO implements Serializable {

    private Long id;

    private Timestamp createTime;

    private String eventType;

    private Long deviceId;

    private String img;
}