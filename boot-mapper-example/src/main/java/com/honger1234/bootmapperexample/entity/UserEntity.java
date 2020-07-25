package com.honger1234.bootmapperexample.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description: 用户实体
 * @author: zt
 * @date: 2020年3月26日
 */
@Data
@Table(name = "tb_user")
public class UserEntity {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private Integer age;
    private String sex;
}
