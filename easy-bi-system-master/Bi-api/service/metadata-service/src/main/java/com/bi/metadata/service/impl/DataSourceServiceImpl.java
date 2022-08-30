package com.bi.metadata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bi.servicebase.entity.DataSource;
import com.bi.metadata.service.DataSourceService;
import com.bi.metadata.mapper.DataSourceMapper;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @author Panda
* @description 针对表【data_source】的数据库操作Service实现
* @createDate 2022-08-20 15:57:14
*/
@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSource>
    implements DataSourceService{


    @Override
    public List<DataSource> queryDSList() {

        return baseMapper.getSourceList();

    }

    @Override
    public List<DataSource> queryDBList(String dataSourceType) {

        return baseMapper.getDbList(dataSourceType);

    }

    @Override
    public List<DataSource> querytableList(String dataSourceType, String dbName) {

        return baseMapper.geTableList(dataSourceType, dbName);

    }

    @Override
    public List<DataSource> queryTableSchema(String dataSourceType, String dbName, String tableName) {

        return baseMapper.getColumnInfo(dataSourceType, dbName, tableName);

    }


}




