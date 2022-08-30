package com.bi.datasource.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/23 16:15
 */

@ApiModel("用户需求信息")
@Data
public class QueryDto {
    @ApiModelProperty("数据集id")
    private String dataSetId;
    @ApiModelProperty("方法集合")
    private List<SelectDto> selectList;
    @ApiModelProperty("条件集合")
    private List<WhereDto> whereList;
    @ApiModelProperty("聚合集合")
    private List<String> groupByList;
    @ApiModelProperty("排序集合")
    private List<SortDto> sort;
}
