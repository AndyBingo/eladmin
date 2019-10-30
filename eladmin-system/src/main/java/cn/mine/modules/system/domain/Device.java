package cn.mine.modules.system.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String deviceName;

    // 流地址
    @Column(name = "stream_addr")
    @NotBlank
    private String streamAddr;

    // IP地址
    @Column(name = "ip_addr")
    @NotBlank
    private String ipAddr;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "nvr_id",nullable = false)
    private Nvr nvr;

    @ManyToMany
    @JoinTable(name = "devices_algorithms", joinColumns = {@JoinColumn(name = "device_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "algorithm_id",referencedColumnName = "id")})
    private Set<Algorithm> algorithms;

}