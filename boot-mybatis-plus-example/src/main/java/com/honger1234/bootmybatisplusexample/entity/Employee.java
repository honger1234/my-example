package com.honger1234.bootmybatisplusexample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: 员工实体
 * @author: zt
 * @date: 2020年3月26日
 */
@Data
@TableName("tb_employee")
public class Employee {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;
//    @TableField(value = "last_name")
    private String lastName;
    private String email ;
    private Integer gender ;
    private Integer age ;
}
