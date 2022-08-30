package com.bi.main.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Panda
 * @date 2022/8/20 23:47
 */

@ApiModel("登录信息 - LoginVo")
@Data
public class LoginVo {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}
