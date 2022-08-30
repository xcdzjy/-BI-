package com.bi.servicebase.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Panda
 * @date 2022/8/21 15:20
 */

@ApiModel("元信息添加信息")
@Data
public class DataSourceCreateDto {

    @ApiModelProperty("数据源名称")
    private String dataSourceName;

    @ApiModelProperty("数据源类型")
    private String dataSourceType;

    @ApiModelProperty("数据库名称")
    private String dbName;

    @ApiModelProperty("数据表名称")
    private String tableName;
}
