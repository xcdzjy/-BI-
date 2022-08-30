package com.bi.main.entity.vo;

import lombok.Data;
import java.util.List;

/**
 * @author Panda
 * @date 2022/8/20 22:40
 */

@Data
public class SchemaListVo {

    private List<ColumnInfoVo> dimensionList;

    private List<ColumnInfoVo> metricList;

    private List<FunctionColumnVo> functionList;
}
