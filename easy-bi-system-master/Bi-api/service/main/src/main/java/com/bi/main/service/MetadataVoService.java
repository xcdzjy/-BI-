package com.bi.main.service;

import com.bi.main.entity.vo.ColumnInfoVo;
import com.bi.main.entity.vo.DBListVo;
import com.bi.main.entity.vo.DataSourceListVo;
import com.bi.main.entity.vo.TableListVo;
import com.bi.servicebase.entity.DataSource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/25 20:20
 */


public interface MetadataVoService {
    List<DataSourceListVo> buildDataSourceListVo(List<DataSource> list);

    List<DBListVo> buildDBListVo(List<DataSource> list);

    List<TableListVo> buildTableListVo(List<DataSource> list);

    List<ColumnInfoVo> buildColumnInfoVo(DataSource dataSource);
}
