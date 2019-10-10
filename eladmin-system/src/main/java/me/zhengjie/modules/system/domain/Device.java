package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Set;

/**
* @author andy
* @date 2019-10-07
*/
@Entity
@Data
@Table(name="device")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "stream_addr")
    private String streamAddr;

    @Column(name = "ip_addr")
    private String ipAddr;

    @ManyToMany
    @JoinTable(name = "devices_algorithms", joinColumns = {@JoinColumn(name = "device_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "algorithm_id",referencedColumnName = "id")})
    private Set<Algorithm> algorithms;

    public void copy(Device source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}