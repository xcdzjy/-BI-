package com.bi.datasource.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/23 16:23
 */

@Data
public class DataVo {
    private String key;
    private List<Object> value;
}
