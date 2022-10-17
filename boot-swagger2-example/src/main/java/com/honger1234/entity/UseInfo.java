package com.honger1234.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UseInfo {

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码",hidden = true)
    private String password;
    @ApiModelProperty(value = "年龄")
    private Integer age;
}
