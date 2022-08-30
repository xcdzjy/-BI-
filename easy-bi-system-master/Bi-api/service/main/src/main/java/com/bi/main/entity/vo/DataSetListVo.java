package com.bi.main.entity.vo;

import com.bi.main.entity.dto.BaseDataSetDto;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @author Panda
 * @date 2022/8/20 16:24
 */

@Data
public class DataSetListVo extends BaseDataSetDto {

    private String id;


    private Date createTime;

    private List<ColumnInfoVo> schema;
}
