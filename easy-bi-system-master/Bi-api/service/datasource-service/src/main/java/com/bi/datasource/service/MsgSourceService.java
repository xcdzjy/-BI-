package com.bi.datasource.service;

import com.bi.datasource.entity.MsgSource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bi.datasource.entity.dto.QueryDto;
import com.bi.servicebase.entity.dto.DataSourceCreateDto;
import com.bi.datasource.entity.vo.QueryVo;
import com.bi.servicebase.entity.DataSource;

/**
* @author Panda
* @description 针对表【tb_msg_source】的数据库操作Service
* @createDate 2022-08-21 14:22:26
*/
public interface MsgSourceService extends IService<MsgSource> {

    DataSource saveMetadata(DataSourceCreateDto dataSourceCreateVo);

    QueryVo queryByRule(QueryDto queryDto);
}
