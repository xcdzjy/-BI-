package com.bi.metadata.mapper;

import com.bi.servicebase.entity.DataSet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bi.servicebase.entity.vo.RuleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Panda
* @description 针对表【data_set】的数据库操作Mapper
* @createDate 2022-08-16 22:18:45
* @Entity com.bi.servicebase.entity.DataSet
*/
public interface DataSetMapper extends BaseMapper<DataSet> {

    List<DataSet> listByRules(@Param("ruleVo") RuleVo ruleVo);


    Long countByRule(@Param("ruleVo") RuleVo ruleVo);

    String info(@Param("id") String dataSetId);

    List<DataSet> myList(@Param("username") String username);
}




