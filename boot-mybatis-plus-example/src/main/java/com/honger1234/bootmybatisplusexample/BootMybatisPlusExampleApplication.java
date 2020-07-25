package com.honger1234.bootmybatisplusexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.honger1234.bootmybatisplusexample.dao")
public class BootMybatisPlusExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisPlusExampleApplication.class, args);
    }

}
