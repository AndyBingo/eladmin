package cn.mine.modules.system.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String name;

    // IP地址
    @Column(name = "ip_addr")
    @NotBlank
    private String ipAddr;

    // 端口
    @Column(name = "port")
    @NotBlank
    private String port;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    // 通道数
    @Column(name = "channel_count")
    private Integer channelCount;
}