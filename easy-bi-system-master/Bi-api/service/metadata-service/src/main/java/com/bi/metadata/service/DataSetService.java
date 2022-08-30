package com.bi.metadata.service;

import com.bi.servicebase.entity.DataSet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bi.servicebase.entity.vo.RuleVo;
import java.util.List;

/**
* @author Panda
* @description 针对表【data_set】的数据库操作Service
* @createDate 2022-08-16 22:18:45
*/
public interface DataSetService extends IService<DataSet> {

    String create(DataSet dataSet);

    List<DataSet> listByRules(RuleVo ruleVo);

    DataSet info(String dataSetId);

    List<DataSet> myList(String username);

    Long countByRule(RuleVo ruleVo);
}
