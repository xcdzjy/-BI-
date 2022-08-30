package com.bi.servicebase.exception;

import com.bi.commonutils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Panda
 * @date 2022/6/19 14:07
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result
                .builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Result<?> error(GlobalException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result
                .builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

}
