package com.bi.main.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/23 16:21
 */
@ApiModel("查询返回信息")
@Data
public class QueryVo {
    @ApiModelProperty("花费时间")
    private Long cost;
    @ApiModelProperty("数据")
    private List<DataVo> data;
}
