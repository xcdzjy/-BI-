package com.bi.main.service.impl;

import com.bi.main.entity.dto.DataSetCreateDto;
import com.bi.main.service.DataSetDtoService;
import com.bi.servicebase.entity.DataSet;
import com.bi.servicebase.uitls.json.JsonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Panda
 * @date 2022/8/25 21:40
 */

@Service
public class DataSetDtoServiceImpl implements DataSetDtoService {

    @Autowired
    private JsonUtil jsonUtil;

    @Override
    public DataSet transferDataSetCreateDto(DataSetCreateDto dataSetCreateDto) {
        DataSet dataSet = new DataSet();
        BeanUtils.copyProperties(dataSetCreateDto,dataSet);
        String schema = jsonUtil.write(dataSetCreateDto.getSchema());
        dataSet.setTableSchema(schema);
        return dataSet;
    }
}
