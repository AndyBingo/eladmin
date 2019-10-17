package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Set;

/**
* @author andy
* @date 2019-10-17
*/
@Entity
@Data
@Table(name="device")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    // 设备名称
    @Column(name = "device_name")
    private String deviceName;

    // 流地址
    @Column(name = "stream_addr")
    private String streamAddr;

    // IP地址
    @Column(name = "ip_addr")
    private String ipAddr;

    @OneToOne
    @JoinColumn(name = "nvr_id",nullable = false)
    private Nvr nvr;

    @ManyToMany
    @JoinTable(name = "devices_algorithms", joinColumns = {@JoinColumn(name = "device_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "algorithm_id",referencedColumnName = "id")})
    private Set<Algorithm> algorithms;

}