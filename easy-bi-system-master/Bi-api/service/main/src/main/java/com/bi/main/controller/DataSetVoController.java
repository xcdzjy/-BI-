package com.bi.main.controller;

import com.bi.commonutils.jwt.JwtHelper;
import com.bi.commonutils.result.Result;
import com.bi.main.entity.dto.DataSetCreateDto;
import com.bi.main.entity.vo.DataSetCreateVo;
import com.bi.main.entity.vo.DataSetVo;
import com.bi.main.entity.vo.SchemaListVo;
import com.bi.main.service.DataSetDtoService;
import com.bi.main.service.DataSetVoService;
import com.bi.main.service.feign.MetaDataFeignService;
import com.bi.main.util.CheckError;
import com.bi.servicebase.controller.BaseController;
import com.bi.servicebase.entity.DataSet;
import com.bi.servicebase.entity.vo.RuleVo;
import com.bi.servicebase.uitls.json.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/20 14:32
 */

@Api(tags = "数据集控制器")
@RequestMapping("/api/v1/dataset")
@RestController
@CrossOrigin
@Slf4j
public class DataSetVoController extends BaseController {

    @Autowired
    private DataSetVoService dataSetVoService;

    @Autowired
    private DataSetDtoService dataSetDtoService;

    @Autowired
    private MetaDataFeignService metaDataFeignService;


    @Autowired
    private JsonUtil jsonUtil;

    private List<DataSet> dataSets;


    @ApiOperation("创建数据集")
    @PostMapping("/create")
    public Result<?> create(@ApiParam("数据集创建数据") @RequestBody DataSetCreateDto dataSetCreateDto){
        String token = request.getHeader("token");
        String nickName = JwtHelper.getNickName(token);
        DataSet dataSet = dataSetDtoService.transferDataSetCreateDto(dataSetCreateDto);
        Result<String> result = metaDataFeignService.create(dataSet);
        CheckError.check(result);
        String id = result.getData();
        dataSet.setId(id);
        log.info("{} 创建了数据集, id 为 {} ...",nickName,id);
        DataSetCreateVo vo = dataSetVoService.build(dataSet);
        return Result.ok(vo);
    }

    @ApiOperation("数据集列表")
    @PostMapping("/list")
    public Result<?> list(@ApiParam("显示规则信息") @RequestBody RuleVo ruleVo){
        Result<List<DataSet>> result = metaDataFeignService.list(ruleVo);
        CheckError.check(result);
        dataSets = result.getData();

        Result<Long> countResult = metaDataFeignService.ruleCount(ruleVo);
        CheckError.check(countResult);
        Long count =  countResult.getData();
        DataSetVo vo = dataSetVoService.buildDataSetVo(dataSets,count);
        return Result.ok(vo);
    }

    @ApiOperation("数据集删除")
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@ApiParam("数据集id") @PathVariable("id") String dataSetId){
        Result<Boolean> result = metaDataFeignService.delete(dataSetId);
        CheckError.check(result);
        log.info("删除了数据集 id 为 {} ...",dataSetId);
        return result;
    }

    @ApiOperation("数据集信息")
    @GetMapping("/info/{dataSetId}")
    public Result<?> info(@ApiParam("数据集id") @PathVariable("dataSetId") String dataSetId){
        Result<DataSet> result = metaDataFeignService.info(dataSetId);
        CheckError.check(result);
        SchemaListVo  vo = dataSetVoService.buildSchemaListVo(result.getData());
        return Result.ok(vo);
    }

    @ApiOperation("获取用户的数据集")
    @GetMapping("/list")
    public Result<?> list(){
        String token = request.getHeader("token");
        String name = JwtHelper.getNickName(token);
        Result<List<DataSet>> result = metaDataFeignService.list(name);
        CheckError.check(result);
        return result;
    }

}
