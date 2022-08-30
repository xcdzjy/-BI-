package com.bi.main.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Panda
 * @date 2022/8/18 22:59
 */

@Data
@ApiModel("列信息 - ColumnInfoVo")
public class ColumnInfoVo {
    @ApiModelProperty("列名称")
    private String name;
    @ApiModelProperty("列类型")
    private String type;
    @ApiModelProperty("描述")
    private String descr;
    @ApiModelProperty("是否分区")
    @JsonProperty("isPartition")
    private boolean isPartition;
}
