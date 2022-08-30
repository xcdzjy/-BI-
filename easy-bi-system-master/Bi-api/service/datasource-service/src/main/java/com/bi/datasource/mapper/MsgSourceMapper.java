package com.bi.datasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.bi.datasource.entity.MsgSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bi.datasource.entity.dto.QueryDto;
import com.bi.datasource.entity.vo.CHMetaVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
* @author Panda
* @description 针对表【tb_msg_source】的数据库操作Mapper
* @createDate 2022-08-21 14:22:26
* @Entity com.bi.datasource.entity.MsgSource
*/
@DS("clickHouse")
public interface MsgSourceMapper extends BaseMapper<MsgSource> {

    @Select("describe ${dbName}.${tableName}")
    List<CHMetaVo> getMetadata(@Param("dbName") String dbName,
                               @Param("tableName")String tableName);


    List<Map<String,Object>> queryByRule(@Param("queryDto") QueryDto queryDto, @Param("tableName") String tableName);

}




