package com.honger1234.util;

import lombok.Data;

/**
 * 统一API响应结果封装
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;
}
