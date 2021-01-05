package com.gjs.fixedassets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*
 * @Description TODO
 * springboot 启动项
 * @Author 顾嘉晟
 * @Date 2021/1/5
 * @params
 * @Return
 **/
@ServletComponentScan
@SpringBootApplication
public class FixedassetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FixedassetsApplication.class, args);
    }

}
