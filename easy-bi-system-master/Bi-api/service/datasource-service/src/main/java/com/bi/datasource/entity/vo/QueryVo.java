package com.bi.datasource.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/23 16:21
 */

@Data
public class QueryVo {
    private Long cost;
    private List<DataVo> data;
}
