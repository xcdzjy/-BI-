package com.bi.metadata.controller;

import com.bi.commonutils.result.Result;
import com.bi.servicebase.entity.DataSource;
import com.bi.metadata.service.DataSourceService;
import com.bi.metadata.utils.ArgsCheck;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Panda
 * @date 2022/8/16 22:27
 */

@Api(tags = "元数据控制器")
@RestController
@CrossOrigin
@Slf4j
public class MetadataController {

    @Autowired
    private DataSourceService dataSourceService;

    @ApiOperation("获取数据源列表")
    @GetMapping("/datasource/list")
    public Result<List<DataSource>> dataSourceList() {
        List<DataSource> list = dataSourceService.queryDSList();
        return Result.ok(list);
    }

    @ApiOperation("获取数据源下的数据库列表")
    @GetMapping("/db/list/{dataSourceType}")
    public Result<List<DataSource>> dbList(@ApiParam("数据源类型") @PathVariable("dataSourceType")String dataSourceType) {
        ArgsCheck.check(dataSourceType);
        List<DataSource> list = dataSourceService.queryDBList(dataSourceType);
        return Result.ok(list);
    }

    @ApiOperation("获取数据源下数据库的数据表列表")
    @GetMapping("/table/list/{dataSourceType}/{dbName}")
    public Result<List<DataSource>> tableList(@ApiParam("数据源类型") @PathVariable("dataSourceType")String dataSourceType,
                                              @ApiParam("数据库名称") @PathVariable("dbName")String dbName) {
        ArgsCheck.check(dataSourceType,dbName);
        List<DataSource> list = dataSourceService.querytableList(dataSourceType, dbName);
        return Result.ok(list);

    }

    @ApiOperation("获取数据源下数据库下数据表元信息")
    @GetMapping("/table/schema/{dataSourceType}/{dbName}/{tableName}")
    public Result<List<DataSource>> tableSchema(@ApiParam("数据源类型") @PathVariable("dataSourceType")String dataSourceType,
                                                @ApiParam("数据库名称") @PathVariable("dbName")String dbName,
                                                @ApiParam("数据表名") @PathVariable("tableName") String tableName) {
        ArgsCheck.check(dataSourceType,dbName,tableName);
        List<DataSource> dataSources = dataSourceService.queryTableSchema(dataSourceType, dbName, tableName);
        return Result.ok(dataSources);
    }

}
