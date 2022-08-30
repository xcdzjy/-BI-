package com.bi.servicebase.entity;

import lombok.Data;

/**
 * @author Panda
 * @date 2022/8/26 14:37
 */

@Data
public class Captcha {
    private String code;
    private String img;
}
