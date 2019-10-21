package me.zhengjie.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.FilterJoinTable;
import org.springframework.data.annotation.CreatedBy;

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
@Table(name="algorithm")
public class Algorithm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 创建时间
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    // 算法名称
    @Column(name = "name")
    private String name;

    // 服务地址
    @Column(name = "service_url")
    private String serviceUrl;

    // 算法参数
    @Column(name = "params")
    private String params;

    // 异常类型
    @Column(name = "exception")
    private String exception;

    @OneToOne
    @JoinColumn(name = "alarm_id")
    private AlarmPolicy alarmPolicy;

    @ManyToMany
    @JoinTable(name = "algorithms_users", joinColumns = {@JoinColumn(name = "algorithm_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")})
    private Set<User> users;

    @JsonIgnore
    @ManyToMany(mappedBy = "algorithms")
    private Set<Device> devices;
}