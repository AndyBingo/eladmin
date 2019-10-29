package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author andy
* @date 2019-10-17
*/
@Entity
@Data
@Table(name="event")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 开始时间
    @Column(name = "start_time")
    private Timestamp startTime;

    // 结束时间
    @Column(name = "end_time")
    private Timestamp endTime;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    @OneToOne
    @JoinColumn(name = "algorithm_id")
    private Algorithm algorithm;

    // 设备ID
    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;

    /**
     * 状态
     */
    @Column(name = "is_closed",nullable = false)
    @NotNull
    private Boolean isClosed;

    @Column(name = "image")
    private String image;

    @Column(name = "video")
    private String video;
}