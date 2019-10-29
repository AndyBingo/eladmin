package cn.mine.service;

import cn.mine.domain.GenConfig;
import cn.mine.domain.vo.ColumnInfo;
import java.util.List;

/**
 * @author andy
 * @date 2019-01-02
 */
public interface GeneratorService {

    /**
     * 查询数据库元数据
     * @param name
     * @param startEnd
     * @return
     */
    Object getTables(String name, int[] startEnd);

    /**
     * 得到数据表的元数据
     * @param name
     * @return
     */
    Object getColumns(String name);

    /**
     * 生成代码
     * @param columnInfos
     * @param genConfig
     * @param tableName
     */
    void generator(List<ColumnInfo> columnInfos, GenConfig genConfig, String tableName);
}
