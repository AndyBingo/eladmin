package cn.mine.modules.system.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author andy
* @date 2019-10-17
*/
@Entity
@Data
@Table(name="alarm_policy")
public class AlarmPolicy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    // 策略名称
    @Column(name = "name")
    private String name;

    // 报警方式
    @Column(name = "alarm_manner")
    private String alarmManner;

    @Column(name = "threshold")
    private Integer threshold;

    @Column(name = "upgrade_alarm_manner")
    private String upgradeAlarmManner;

    @Column(name = "alarm_interval")
    private Integer alarmInterval;
}