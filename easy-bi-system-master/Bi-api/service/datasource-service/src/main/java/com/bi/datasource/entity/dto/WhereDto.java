package com.bi.datasource.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/23 16:13
 */

@ApiModel("条件信息")
@Data
public class WhereDto {
    @ApiModelProperty("列名字")
    private String field;
    @ApiModelProperty("操作")
    private String op;
    @ApiModelProperty("连接符号(AND 或者 OR)")
    private String joiner;
    @ApiModelProperty("值集合")
    private List<Object> valueList;
}
