package com.bi.main.entity.dto;

import com.bi.main.entity.vo.ColumnInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/20 21:53
 */

@Data
@ApiModel("数据集创建信息 - DataSetCreateDto")
public class DataSetCreateDto extends BaseDataSetDto{

    @ApiModelProperty("元数据信息")
    private List<ColumnInfoVo> schema;
}
