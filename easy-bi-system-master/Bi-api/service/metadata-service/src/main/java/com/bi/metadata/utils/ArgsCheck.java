package com.bi.metadata.utils;

import com.bi.commonutils.result.ResultCode;
import com.bi.servicebase.exception.GlobalException;
import org.springframework.util.StringUtils;

/**
 * @author Panda
 * @date 2022/8/19 19:40
 */

public class ArgsCheck {

    public static void check(String... args){
        for (String arg : args) {
            if (StringUtils.isEmpty(arg)){
                throw new GlobalException(ResultCode.ARGUMENT_VALID_ERROR.getCode()
                        ,ResultCode.ARGUMENT_VALID_ERROR.getMessage());
            }
        }

    }
}
