package com.bi.datasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bi.servicebase.entity.DataSource;
import org.apache.ibatis.annotations.Param;

/**
* @author Panda
* @description 针对表【data_source】的数据库操作Service
* @createDate 2022-08-21 14:50:23
*/
public interface DataSourceService extends IService<DataSource> {


    String selectTableName(@Param("id") String dataSetId);
}
