package com.bi.main.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Abraham
 * @Date 2022/8/19 9:35
 * @Version 1.0
 */

@Data
@ApiModel("注册信息 - RegisterVo")
public class RegisterVo {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码")
    private String code;
}
