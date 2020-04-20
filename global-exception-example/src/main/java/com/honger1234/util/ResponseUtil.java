package com.honger1234.util;

import com.honger1234.constant.ResultCode;

/**
 * 响应结果生成工具
 */
public class ResponseUtil {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        Result<Object> objectResult = new Result<>();
        objectResult.setCode(ResultCode.SUCCESS.code());
        objectResult.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return objectResult;
    }

    public static <T> Result<T> genSuccessResult(T data) {
        Result<T> objectResult = new Result<>();
        objectResult.setCode(ResultCode.SUCCESS.code());
        objectResult.setMessage(DEFAULT_SUCCESS_MESSAGE);
        objectResult.setData(data);
        return objectResult;
    }

    public static Result genFailResult(String message) {
        Result objectResult = new Result<>();
        objectResult.setCode(ResultCode.FAIL.code());
        objectResult.setMessage(message);
        return objectResult;
    }
}
