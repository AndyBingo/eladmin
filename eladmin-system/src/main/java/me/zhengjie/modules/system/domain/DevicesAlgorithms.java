package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author andy
* @date 2019-10-07
*/
@Entity
@Data
@Table(name="devices_algorithms")
public class DevicesAlgorithms implements Serializable {

    // 设备ID
    @Id
    @Column(name = "device_id")
    private Long deviceId;

    // 算法ID
    @Id
    @Column(name = "algorithm_id")
    private Long algorithmId;

    public void copy(DevicesAlgorithms source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}