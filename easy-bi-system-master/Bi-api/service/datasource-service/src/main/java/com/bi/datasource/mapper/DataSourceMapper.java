package com.bi.datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bi.servicebase.entity.DataSource;
import org.apache.ibatis.annotations.Select;

/**
* @author Panda
* @description 针对表【data_source】的数据库操作Mapper
* @createDate 2022-08-21 14:50:23
* @Entity com.bi.datasource.entity.DataSource
*/
public interface DataSourceMapper extends BaseMapper<DataSource> {

    @Select("select `table_name` from  `data_set` where `id` = #{id}")
    String selectTableName(String dataSetId);
}




