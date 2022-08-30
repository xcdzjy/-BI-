package com.bi.main.service.impl;

import com.bi.main.entity.vo.*;
import com.bi.main.service.DataSetVoService;
import com.bi.servicebase.entity.DataSet;
import com.bi.servicebase.uitls.json.JsonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Panda
 * @date 2022/8/25 21:37
 */

@Service
public class DataSetVoServiceImpl implements DataSetVoService {

    @Autowired
    private JsonUtil jsonUtil;

    private final List<ColumnInfoVo> dimensionList = new ArrayList<>();
    private final List<ColumnInfoVo> metricList = new ArrayList<>();
    private final List<FunctionColumnVo> functionList = new ArrayList<FunctionColumnVo>(){{
        add(new FunctionColumnVo("求和","sum"));
        add(new FunctionColumnVo("最大","max"));
        add(new FunctionColumnVo("最小","min"));
        add(new FunctionColumnVo("平均值","avg"));
        add(new FunctionColumnVo("统计","count"));
    }};

    private final Set<String> metricTypes = new HashSet<String>(){{
        add("int");
        add("bigint");
        add("long");
        add("float");
        add("double");
        add("decimal");
        add("bigdecimal");

    }};

    @Override
    public DataSetCreateVo build(DataSet dataSet) {
        DataSetCreateVo vo = new DataSetCreateVo();
        vo.setDataSetId(dataSet.getId());
        return vo;
    }

    @Override
    public DataSetVo buildDataSetVo(List<DataSet> dataSets, Long count) {
        DataSetVo dataSetVo = new DataSetVo();
        List<DataSetListVo> list = new ArrayList<>();
        dataSetVo.setTotalCount(count);

        dataSets.forEach(dataSet -> {
            DataSetListVo dataSetListVo = new DataSetListVo();
            BeanUtils.copyProperties(dataSet,dataSetListVo);
            DataSetListVo dataSetListVo1 = new DataSetListVo();
            List<ColumnInfoVo> columInfos = jsonUtil.read(dataSet.getTableSchema(), ColumnInfoVo.class);
            dataSetListVo.setSchema(columInfos);
            list.add(dataSetListVo);
        });

        dataSetVo.setDataSetList(list);
        return dataSetVo;
    }

    @Override
    public SchemaListVo buildSchemaListVo(DataSet dataSet) {
        List<ColumnInfoVo> columnInfos = jsonUtil.read(dataSet.getTableSchema(), ColumnInfoVo.class);
        metricList.clear();
        dimensionList.clear();
        columnInfos.stream().forEach(columnInfo -> {
            boolean isMetric = false;
            for (String metricType : metricTypes) {
                if (columnInfo.getType().toLowerCase().startsWith(metricType)){
                    metricList.add(columnInfo);
                    isMetric = true;
                    break;
                }

            }
            if (!isMetric){
                dimensionList.add(columnInfo);
            }
        });

        SchemaListVo schemaListVo = new SchemaListVo();
        schemaListVo.setDimensionList(dimensionList);
        schemaListVo.setMetricList(metricList);
        schemaListVo.setFunctionList(functionList);

        return schemaListVo;
    }
}
