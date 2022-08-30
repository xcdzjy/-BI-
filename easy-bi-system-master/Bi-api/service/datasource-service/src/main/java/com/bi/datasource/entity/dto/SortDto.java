package com.bi.datasource.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Panda
 * @date 2022/8/23 16:14
 */

@ApiModel("排序条件信息")
@Data
public class SortDto {
    @ApiModelProperty("列名称")
    private String field;
    @ApiModelProperty("升序(ASC)或者降序(DESC)")
    private String order;
}
