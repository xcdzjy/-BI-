package com.bi.main.service.impl;

import com.bi.main.entity.vo.ColumnInfoVo;
import com.bi.main.entity.vo.DBListVo;
import com.bi.main.entity.vo.DataSourceListVo;
import com.bi.main.entity.vo.TableListVo;
import com.bi.main.service.MetadataVoService;
import com.bi.servicebase.entity.DataSource;
import com.bi.servicebase.uitls.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Panda
 * @date 2022/8/25 20:21
 */

@Service
public class MetadataVoServiceImpl implements MetadataVoService {

    @Autowired
    private JsonUtil jsonUtil;

    @Override
    public List<DataSourceListVo> buildDataSourceListVo(List<DataSource> list) {
        List<DataSourceListVo> vos = new ArrayList<>();
        list.forEach(dataSource -> {
            DataSourceListVo dataSourceListVo = new DataSourceListVo();
            dataSourceListVo.setDataSourceName(dataSource.getDataSourceName());
            dataSourceListVo.setDataSourceType(dataSource.getDataSourceType());
            vos.add(dataSourceListVo);
        });

        return vos;
    }

    @Override
    public List<DBListVo> buildDBListVo(List<DataSource> list) {
        List<DBListVo> vos = new ArrayList<>();
        list.forEach(dataSource -> {
            DBListVo dbListVo = new DBListVo();
            dbListVo.setDbName(dataSource.getDbName());
            vos.add(dbListVo);
        });

        return vos;
    }

    @Override
    public List<TableListVo> buildTableListVo(List<DataSource> list) {
        List<TableListVo> vos = new ArrayList<>();
        list.forEach(dataSource -> {
            TableListVo tableListVo = new TableListVo();
            tableListVo.setTableName(dataSource.getTableName());
            vos.add(tableListVo);
        });

        return vos;
    }

    @Override
    public List<ColumnInfoVo> buildColumnInfoVo(DataSource dataSource) {
        return jsonUtil.read(dataSource.getTableSchema(), ColumnInfoVo.class);
    }
}
