package com.bi.main.controller;

import com.bi.commonutils.result.Result;
import com.bi.main.entity.vo.ColumnInfoVo;
import com.bi.main.entity.vo.DBListVo;
import com.bi.main.entity.vo.DataSourceListVo;
import com.bi.main.entity.vo.TableListVo;
import com.bi.main.service.MetadataVoService;
import com.bi.main.service.feign.MetaDataFeignService;
import com.bi.main.util.CheckError;
import com.bi.servicebase.controller.BaseController;
import com.bi.servicebase.entity.DataSource;
import com.bi.servicebase.uitls.json.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/16 22:27
 */

@Api(tags = "元数据控制器")
@RestController
@RequestMapping("/api/v1/metadata")
@CrossOrigin
@Slf4j
public class MetadataVoController extends BaseController {


    @Autowired
    private MetadataVoService metadataService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MetaDataFeignService metaDataFeignService;

    @Autowired
    private JsonUtil jsonUtil;

    private List<DataSource> list;


    private final String BASE_URL = "http://localhost:8001/api/metadata";

    @ApiOperation("获取数据源列表")
    @GetMapping("/datasource/list")
    public Result<?> dataSourceList(){
        String datasourceListStr = stringRedisTemplate.opsForValue().get("datasource:list");

        if (StringUtils.isEmpty(datasourceListStr)){
            Result<List<DataSource>> result = metaDataFeignService.dataSourceList();
            CheckError.check(result);
            list = result.getData();
            String value = jsonUtil.write(list);
            stringRedisTemplate.opsForValue().set("datasource:list",value);
        }else {
            list = jsonUtil.read(datasourceListStr,DataSource.class);
        }

        List<DataSourceListVo> vo = metadataService.buildDataSourceListVo(list);
        return Result.ok(vo);
    }

    @ApiOperation("获取数据源下的数据库列表")
    @GetMapping("/db/list/{dataSourceType}")
    public Result<?> dbList(@ApiParam("数据源类型") @PathVariable("dataSourceType")String dataSourceType){
        String key = "datasource:" + dataSourceType + ":db:list";
        String dbListStr = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isEmpty(dbListStr)){
            Result<List<DataSource>> result = metaDataFeignService.dbList(dataSourceType);
            CheckError.check(result);
            list = result.getData();
            String value = jsonUtil.write(list);
            stringRedisTemplate.opsForValue().set(key,value);
        }else {
            list = jsonUtil.read(dbListStr,DataSource.class);
        }


        List<DBListVo> vo = metadataService.buildDBListVo(list);
        return Result.ok(vo);
    }

    @ApiOperation("获取数据源下数据库的数据表列表")
    @GetMapping("/table/list/{dataSourceType}/{dbName}")
    public Result<?> tableList(@ApiParam("数据源类型") @PathVariable("dataSourceType")String dataSourceType,
                               @ApiParam("数据库名称") @PathVariable("dbName")String dbName){
        String key = "datasource:" + dataSourceType + ":db:" + dbName +":list";
        String tableListStr = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isEmpty(tableListStr)){
            Result<List<DataSource>> result = metaDataFeignService.tableList(dataSourceType, dbName);
            CheckError.check(result);
            list = result.getData();
            String value = jsonUtil.write(list);
            stringRedisTemplate.opsForValue().set(key,value);
        }else {
            list = jsonUtil.read(tableListStr,DataSource.class);
        }

        List<TableListVo> vo = metadataService.buildTableListVo(list);
        return Result.ok(vo);
    }

    @ApiOperation("获取数据源下数据库下数据表元信息")
    @GetMapping("/table/schema/{dataSourceType}/{dbName}/{tableName}")
    public Result<?> tableSchema(@ApiParam("数据源类型") @PathVariable("dataSourceType")String dataSourceType,
                                 @ApiParam("数据库名称") @PathVariable("dbName")String dbName,
                                 @ApiParam("数据表名") @PathVariable("tableName") String tableName) {
        String key = "datasource:" + dataSourceType + ":db:" + dbName +":table:" + tableName + ":tableSchema";
        String schemaListStr = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isEmpty(schemaListStr)){
            Result<List<DataSource>> result = metaDataFeignService.tableSchema(dataSourceType, dbName, tableName);
            CheckError.check(result);
            list = result.getData();
            String value = jsonUtil.write(list);
            stringRedisTemplate.opsForValue().set(key,value);
        }else {
            list = jsonUtil.read(schemaListStr,DataSource.class);
        }

        List<ColumnInfoVo> vo = metadataService.buildColumnInfoVo(list.get(0));
        return Result.ok(vo);
    }

}
