package com.honger1234.restfulapi.entity;

import lombok.Data;

/**
 * @Description: 用户实体类
 * @author: zt
 * @date: 2020年3月26日
 */
@Data
public class UserEntity {
    private Long id;
    private String name;
    private Integer age;
}
