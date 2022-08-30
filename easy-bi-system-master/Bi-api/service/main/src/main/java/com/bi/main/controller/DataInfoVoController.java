package com.bi.main.controller;

import com.bi.commonutils.result.Result;
import com.bi.main.entity.vo.QueryVo;
import com.bi.main.service.feign.DataInfoFeignService;
import com.bi.main.util.CheckError;
import com.bi.servicebase.controller.BaseController;
import com.bi.servicebase.entity.DataSource;
import com.bi.servicebase.entity.dto.DataSourceCreateDto;
import com.bi.servicebase.entity.dto.QueryDto;
import com.bi.servicebase.uitls.json.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

/**
 * @author Panda
 * @date 2022/8/21 14:35
 */

@Api(tags = "源数据控制器")
@RestController
@RequestMapping("/api/v1/dataInfo")
@CrossOrigin
@Slf4j
public class DataInfoVoController extends BaseController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private DataInfoFeignService dataInfoFeignService;

    @Autowired
    private JsonUtil jsonUtil;


    @ApiOperation("添加数据源元信息")
    @PostMapping("/saveMetadata")
    public Result<?> saveMetadata(@ApiParam("元信息添加信息") @RequestBody DataSourceCreateDto dataSourceCreateDto){
        Result<DataSource> result = dataInfoFeignService.saveMetadata(dataSourceCreateDto);
        CheckError.check(result);
        DataSource dataSource = result.getData();
        assert dataSource != null;
        log.info("添加了元信息 id为 {} ...",dataSource.getId());
        Set<String> keys = stringRedisTemplate.keys("datasource:*");
        if (keys!=null){
            stringRedisTemplate.delete(keys);
        }
        return Result.ok(dataSource);
    }

    @ApiOperation("根据用户需求查询数据")
    @PostMapping("/query")
    public Result<?> query(@ApiParam("用户需求")@RequestBody QueryDto queryDto){
        Result<QueryVo> result = dataInfoFeignService.query(queryDto);
        CheckError.check(result);
        QueryVo queryVo = result.getData();
        assert queryVo != null;
        log.info("对 {} 数据集进行查询，耗费了 {} ms",queryDto.getDataSetId(),queryVo.getCost());
        return Result.ok(queryVo);
    }


}
