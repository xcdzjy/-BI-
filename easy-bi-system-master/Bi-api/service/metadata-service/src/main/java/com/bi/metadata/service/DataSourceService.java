package com.bi.metadata.service;

import com.bi.servicebase.entity.DataSource;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
* @author Panda
* @description 针对表【data_source】的数据库操作Service
* @createDate 2022-08-20 15:57:14
*/
public interface DataSourceService extends IService<DataSource> {

    List<DataSource> queryDSList();

    List<DataSource> queryDBList(String dataSourceType);

    List<DataSource> querytableList(String dataSourceType, String dbName);

    List<DataSource> queryTableSchema(String dataSourceType, String dbName, String tableName);

}
