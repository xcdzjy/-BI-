package com.bi.servicebase.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Panda
 * @date 2022/8/23 16:13
 */

@ApiModel("方法条件信息")
@Data
public class SelectDto {
    @ApiModelProperty("方法名")
    private String function;
    @ApiModelProperty("列名")
    private String field;
}
