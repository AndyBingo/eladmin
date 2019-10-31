package cn.mine.modules.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;


/**
* @author andy
* @date 2019-10-17
*/
@Data
public class NvrDTO implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    // NVR名称
    private String name;

    // IP地址
    private String ipAddr;

    // 端口
    private String port;

    private String userName;

    private String password;

    // 通道数
    private Integer channelCount;
}