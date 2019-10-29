package me.zhengjie.modules.quartz.domain;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Data
@Entity
@Table(name = "quartz_job")
public class QuartzJob implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Update.class})
    private Long id;

    /**
     * 定时器名称
     */
    @Column(name = "job_name", unique = true)
    private String jobName;

    /**
     * Bean名称
     */
    @Column(name = "bean_name", nullable = false, columnDefinition = "varchar(255) default 'alarmTask'")
    @NotBlank
    private String beanName;

    /**
     * 方法名称
     */
    @Column(name = "method_name", nullable = false, columnDefinition = "varchar(255) default 'run'")
    @NotBlank
    private String methodName;

    /**
     * 参数
     */
    @Column(name = "params")
    private String params;

    /**
     * cron表达式
     */
    @Column(name = "interval_sec")
    @NotBlank
    private Integer intervalSec;

    /**
     * 状态
     */
    @Column(name = "is_pause")
    private Boolean isPause = false;

    /**
     * 备注
     */
    @Column(name = "remark")
    @NotBlank
    private String remark;

    /**
     * 创建日期
     */
    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "count",nullable = false, columnDefinition = "int default 0")
    private Integer count;

    public interface Update{}
}