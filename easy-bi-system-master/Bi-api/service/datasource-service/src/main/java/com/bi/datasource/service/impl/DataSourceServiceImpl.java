package com.bi.datasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bi.datasource.service.DataSourceService;
import com.bi.datasource.mapper.DataSourceMapper;
import com.bi.servicebase.entity.DataSource;
import org.springframework.stereotype.Service;

/**
* @author Panda
* @description 针对表【data_source】的数据库操作Service实现
* @createDate 2022-08-21 14:50:23
*/
@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSource>
    implements DataSourceService{

    @Override
    public String selectTableName(String dataSetId) {
        return baseMapper.selectTableName(dataSetId);
    }
}




