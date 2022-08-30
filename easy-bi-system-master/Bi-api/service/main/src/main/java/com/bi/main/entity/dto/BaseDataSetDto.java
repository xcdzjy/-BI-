package com.bi.main.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Panda
 * @date 2022/8/20 21:47
 */

@Data
public abstract class BaseDataSetDto {
    @ApiModelProperty("数据集名称")
    private String name;

    @ApiModelProperty("数据集描述")
    private String descr;

    @ApiModelProperty("数据集类型")
    private String dataSourceType;

    @ApiModelProperty("数据库名称")
    private String dbName;

    @ApiModelProperty("数据表名称")
    private String tableName;

    @ApiModelProperty("创建者")
    private String createUser;
}
