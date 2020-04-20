package com.honger1234.exception;

import com.honger1234.constant.ResultCode;
import com.honger1234.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 全局异常处理类
 * @author: zt
 * @date: 2020年3月26日
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public Object exceptionHandler(Exception e, HttpServletRequest req, Object handler) {

        Result result = new Result();
        if (e instanceof BusinessException) {//业务失败的异常，如“账号或密码错误”
            result.setCode(ResultCode.FAIL.code());
            result.setMessage(e.getMessage());
            logger.info(e.getMessage());
        } else if (e instanceof NoHandlerFoundException) {//404异常
            result.setCode(ResultCode.NOT_FOUND.code());
            result.setMessage("接口 [" + req.getRequestURI() + "] 不存在");
//            result.setMessage("你找的页面被小偷偷走了，请联系管理员抓小偷");
            logger.info(e.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {//请求方式不对
            result.setCode(ResultCode.METHOD_NOT_ALLOWED.code());
            result.setMessage("接口 [" + req.getRequestURI() + "] 请求方式不对，请换个姿势操作试试！");
            logger.info(e.getMessage());
        } else if (e instanceof NullPointerException) {//空指针异常
            result.setCode(ResultCode.FAIL.code());
            result.setMessage("接口 [" + req.getRequestURI() + "] 空指针异常");
            logger.info(e.getMessage());
        } else if (e instanceof ServletException) {
            result.setCode(ResultCode.FAIL.code());
            result.setMessage(e.getMessage());
            logger.info(e.getMessage());
        } else {
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR.code());
            result.setMessage("接口 [" + req.getRequestURI() + "] 内部错误");
            String message;
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                        req.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        e.getMessage());
            } else {
                message = e.getMessage();
            }
            logger.error(message, e);
        }
        return result;
    }
}
