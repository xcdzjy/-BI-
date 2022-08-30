package com.bi.metadata.controller;

import com.bi.commonutils.result.Result;
import com.bi.servicebase.entity.DataSet;
import com.bi.servicebase.entity.vo.RuleVo;
import com.bi.metadata.service.DataSetService;
import com.bi.servicebase.controller.BaseController;
import com.bi.servicebase.exception.GlobalException;
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
@RequestMapping("/dataset")
@RestController
@CrossOrigin
@Slf4j
public class DataSetController extends BaseController {

    @Autowired
    private DataSetService dataSetService;

    @ApiOperation("创建数据集")
    @PostMapping("/create")
    public Result<String> create(@ApiParam("数据集创建数据") @RequestBody DataSet dataSet){
        String id = dataSetService.create(dataSet);
        return Result.ok(id);
    }

    @ApiOperation("数据集列表")
    @PostMapping("/list")
    public Result<List<DataSet>> list(@ApiParam("显示规则信息") @RequestBody RuleVo ruleVo){
        List<DataSet> list =  dataSetService.listByRules(ruleVo);
        return Result.ok(list);
    }

    @ApiOperation("数据集列表")
    @PostMapping("/ruleCount")
    public Result<Long> ruleCount(@ApiParam("显示规则信息") @RequestBody RuleVo ruleVo){
        Long count =  dataSetService.countByRule(ruleVo);
        return Result.ok(count);
    }

    @ApiOperation("数据集删除")
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@ApiParam("数据集id") @PathVariable("id") String dataSetId){
        boolean remove = dataSetService.removeById(dataSetId);
        if (!remove){
            throw new GlobalException(201,"数据集删除失败");
        }
        return Result.ok(true);
    }

    @ApiOperation("数据集信息")
    @GetMapping("/info/{dataSetId}")
    public Result<DataSet> info(@ApiParam("数据集id") @PathVariable("dataSetId")String dataSetId){
        DataSet dataSet =  dataSetService.info(dataSetId);
        return Result.ok(dataSet);
    }

    @ApiOperation("获取用户的数据集")
    @GetMapping("/list/{username}")
    public Result<List<DataSet>> list(@PathVariable("username")String username){
        List<DataSet> list =  dataSetService.myList(username);
        return Result.ok(list);
    }


}
