package com.bi.main.service.feign;

import com.bi.commonutils.result.Result;
import com.bi.servicebase.entity.DataSet;
import com.bi.servicebase.entity.DataSource;
import com.bi.servicebase.entity.vo.RuleVo;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Panda
 * @data 2022/8/27 15:14
 */
@Component
@FeignClient("metadata-service")
public interface MetaDataFeignService {

    @GetMapping("/datasource/list")
    public Result<List<DataSource>> dataSourceList();

    @GetMapping("/db/list/{dataSourceType}")
    public Result<List<DataSource>> dbList(@ApiParam("数据源类型")
                                               @PathVariable("dataSourceType")String dataSourceType);

    @GetMapping("/table/list/{dataSourceType}/{dbName}")
    public Result<List<DataSource>> tableList(@ApiParam("数据源类型") @PathVariable("dataSourceType")String dataSourceType,
                                              @ApiParam("数据库名称") @PathVariable("dbName")String dbName);

    @GetMapping("/table/schema/{dataSourceType}/{dbName}/{tableName}")
    public Result<List<DataSource>> tableSchema(@ApiParam("数据源类型") @PathVariable("dataSourceType")String dataSourceType,
                                                @ApiParam("数据库名称") @PathVariable("dbName")String dbName,
                                                @ApiParam("数据表名") @PathVariable("tableName") String tableName);
    @PostMapping("/dataset/create")
    public Result<String> create(@ApiParam("数据集创建数据") @RequestBody DataSet dataSet);

    @PostMapping("/dataset/list")
    public Result<List<DataSet>> list(@ApiParam("显示规则信息") @RequestBody RuleVo ruleVo);

    @PostMapping("/dataset/ruleCount")
    public Result<Long> ruleCount(@ApiParam("显示规则信息") @RequestBody RuleVo ruleVo);

    @PostMapping("/dataset/delete/{id}")
    public Result<Boolean> delete(@ApiParam("数据集id") @PathVariable("id") String dataSetId);

    @GetMapping("/dataset/info/{dataSetId}")
    public Result<DataSet> info(@ApiParam("数据集id") @PathVariable("dataSetId")String dataSetId);

    @GetMapping("/dataset/list/{username}")
    public Result<List<DataSet>> list(@PathVariable("username")String username);
}
