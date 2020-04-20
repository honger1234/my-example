package com.honger1234.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 继承ErrorController接口重写getErrorPath方法，处理404异常
 * @author: zt
 * @date: 2020年3月26日
 */
@Controller
public class NotFoundException implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public Object error() {
        return "404";
    }

}
