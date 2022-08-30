package com.bi.datasource.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Panda
 * @date 2022/8/21 14:30
 */

@Data
public class CHMetaVo {

    private String name;

    private String type;

    @JsonProperty("descr")
    private String comment;

    @JsonProperty("isPartition")
    private boolean isPartition = false;

}
