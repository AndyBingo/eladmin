package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import me.zhengjie.modules.system.domain.AlarmPolicy;

import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Set;


/**
* @author andy
* @date 2019-10-17
*/
@Data
public class AlgorithmDTO implements Serializable {

    private Long id;

    // 创建时间
    private Timestamp createTime;

    // 算法名称
    private String name;

    // 服务地址
    private String serviceUrl;

    // 算法参数
    private String params;

    // 异常类型
    private String exception;

    private Long alarmId;

    private AlarmPolicyDTO alarmPolicy;

    private Set<UserDTO> users;
}