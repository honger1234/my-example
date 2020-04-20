package com.honger1234.exception;

/**
 * @Description: 服务（业务）异常如“ 账号或密码错误 ”
 * @author: zt
 * @date: 2020年3月26日
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
