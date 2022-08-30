package com.bi.main.service;

import com.bi.main.entity.vo.DataSetCreateVo;
import com.bi.main.entity.vo.DataSetVo;
import com.bi.main.entity.vo.SchemaListVo;
import com.bi.servicebase.entity.DataSet;

import java.util.List;

/**
 * @author Panda
 * @data 2022/8/25 21:36
 */
public interface DataSetVoService {
    DataSetCreateVo build(DataSet dataSet);

    DataSetVo buildDataSetVo(List<DataSet> dataSets, Long count);

    SchemaListVo buildSchemaListVo(DataSet dataSet);
}
