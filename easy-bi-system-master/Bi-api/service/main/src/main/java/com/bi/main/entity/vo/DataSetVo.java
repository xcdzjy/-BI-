package com.bi.main.entity.vo;

import lombok.Data;
import java.util.List;

/**
 * @author Panda
 * @date 2022/8/20 16:04
 */

@Data
public class DataSetVo {

    private List<DataSetListVo> dataSetList;

    private Long totalCount;
}
