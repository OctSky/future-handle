package com.zjh.future.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangjh
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public Object handleException(Exception r) {
        return null;
    }
}
