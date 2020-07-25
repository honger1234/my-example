package com.honger1234.bootmapperexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.honger1234.bootmapperexample.dao")
public class BootMapperExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMapperExampleApplication.class, args);
    }

}
