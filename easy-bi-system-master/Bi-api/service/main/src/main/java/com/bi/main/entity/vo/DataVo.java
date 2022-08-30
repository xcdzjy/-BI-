package com.bi.main.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/23 16:23
 */
@ApiModel("查询数据信息")
@Data
public class DataVo {
    @ApiModelProperty("列名")
    private String key;
    @ApiModelProperty("列值")
    private List<Object> value;
}
