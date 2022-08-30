package com.bi.servicebase.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * @author Panda
 * @date 2022/8/20 15:21
 */

@Data
@ApiModel("数据集列表展现规则 - RuleVo")
public class RuleVo {

    @ApiModelProperty("排序列")
    private String orderBy;

    @ApiModelProperty("排序方式")
    private String order;

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页涵盖信息数量")
    private Integer pageSize;

    @ApiModelProperty("模糊查询关键字")
    private String  keyword;

    @ApiModelProperty("创建者")
    private String createUser;

    @ApiModelProperty(hidden = true)
    private Integer key;

    public Integer getKey() {
        return (this.page - 1) * pageSize;
    }
}
