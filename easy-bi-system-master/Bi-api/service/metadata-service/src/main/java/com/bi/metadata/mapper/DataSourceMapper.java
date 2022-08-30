package com.bi.metadata.mapper;

import com.bi.servicebase.entity.DataSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Panda
* @description 针对表【data_source】的数据库操作Mapper
* @createDate 2022-08-20 15:57:14
* @Entity com.bi.servicebase.entity.DataSource
*/
public interface DataSourceMapper extends BaseMapper<DataSource> {

    @Select("SELECT * FROM `data_source`")
    List<DataSource> getAll();

    @Select("SELECT DISTINCT `data_source_type`,`data_source_name` FROM `data_source`")
    List<DataSource> getSourceList();

    @Select("SELECT DISTINCT `db_name`,`data_source_type`,`data_source_name`FROM `data_source` " +
            "WHERE `data_source_type` = #{dataSourceType}")
    List<DataSource> getDbList(@Param("dataSourceType") String dataSourceType);


    @Select("SELECT DISTINCT `table_name`,`db_name`,`data_source_type`,`data_source_name`" +
            "FROM `data_source`" +
            "WHERE `data_source_type` = #{dataSourceType} AND `db_name` = #{dbName}")
    List<DataSource> geTableList(@Param("dataSourceType") String dataSourceType,
                                 @Param("dbName") String dbName);


    @Select("SELECT `table_schema`,`table_name`,`db_name`,`data_source_type`,`data_source_name`" +
            "FROM `data_source`" +
            "WHERE `data_source_type` = #{dataSourceType} AND `db_name` = #{dbName} AND `table_name` = #{tableName}")
    List<DataSource> getColumnInfo(@Param("dataSourceType") String dataSourceType,
                                   @Param("dbName") String dbName,
                                   @Param("tableName") String tableName);
}




