package me.zhengjie.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="algorithm")
public class Algorithm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "name")
    private String name;

    @Column(name = "service_url")
    private String serviceUrl;

    @JsonIgnore
    @ManyToMany(mappedBy = "algorithms")
    private Set<Device> devices;

    public void copy(Algorithm source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}