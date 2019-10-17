package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author andy
* @date 2019-10-17
*/
@Entity
@Data
@Table(name="nvr")
public class Nvr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // NVR名称
    @Column(name = "name")
    private String name;

    // IP地址
    @Column(name = "ip_addr")
    private String ipAddr;

    // 端口
    @Column(name = "port")
    private String port;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    // 通道数
    @Column(name = "channel_count")
    private Integer channelCount;
}