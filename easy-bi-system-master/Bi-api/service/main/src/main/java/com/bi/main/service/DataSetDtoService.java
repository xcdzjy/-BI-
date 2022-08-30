package com.bi.main.service;

import com.bi.main.entity.dto.DataSetCreateDto;
import com.bi.servicebase.entity.DataSet;

/**
 * @author Panda
 * @data 2022/8/25 21:39
 */
public interface DataSetDtoService {
    DataSet transferDataSetCreateDto(DataSetCreateDto dataSetCreateDto);
}
