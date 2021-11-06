package com.pengcheng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pengcheng.dao")
public class QqhuaxiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(QqhuaxiaApplication.class, args);
    }

}
