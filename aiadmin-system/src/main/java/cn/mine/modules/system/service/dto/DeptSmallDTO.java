package cn.mine.modules.system.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author andy
* @date 2019-6-10 16:32:18
*/
@Data
public class DeptSmallDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;
}