package com.bi.datasource.controller;

import com.bi.commonutils.result.Result;
import com.bi.servicebase.entity.dto.DataSourceCreateDto;
import com.bi.datasource.entity.dto.QueryDto;
import com.bi.datasource.entity.vo.QueryVo;
import com.bi.datasource.service.MsgSourceService;
import com.bi.servicebase.entity.DataSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Panda
 * @date 2022/8/21 14:35
 */

@Api(tags = "源数据控制器")
@RestController
@CrossOrigin
@Slf4j
public class DataInfoController {

    @Autowired
    private MsgSourceService msgSourceService;

    @ApiOperation("添加数据源元信息")
    @PostMapping("/saveMetadata")
    public Result<DataSource> saveMetadata(@ApiParam("元信息添加信息") @RequestBody DataSourceCreateDto dataSourceCreateVo){
        DataSource dataSource = msgSourceService.saveMetadata(dataSourceCreateVo);
        return Result.ok(dataSource);
    }

    @ApiOperation("根据用户需求查询数据")
    @PostMapping("/query")
    public Result<QueryVo> query(@ApiParam("用户需求")@RequestBody QueryDto queryDto){
        QueryVo queryVo = msgSourceService.queryByRule(queryDto);
        return Result.ok(queryVo);
    }


}
