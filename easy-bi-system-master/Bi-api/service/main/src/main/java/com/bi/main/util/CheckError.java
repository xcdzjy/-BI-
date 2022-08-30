package com.bi.main.util;

import com.bi.commonutils.result.Result;
import com.bi.servicebase.exception.GlobalException;

/**
 * @author Panda
 * @date 2022/8/26 1:13
 */

public class CheckError {
    public static void check(Result result){
        if (result.getCode()!= 200){
            throw new GlobalException(result.getCode(), result.getMessage());
        }
    }
}
