package cn.mine.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author andy
* @date 2019-10-17
*/
@Data
public class AlarmPolicyDTO implements Serializable {

    private Long id;

    private Timestamp createTime;

    // 策略名称
    private String name;

    // 报警方式
    private String alarmManner;

    private Integer threshold;

    private String upgradeAlarmManner;

    private Integer alarmInterval;
}