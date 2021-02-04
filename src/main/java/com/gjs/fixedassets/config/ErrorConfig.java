package com.gjs.fixedassets.config;


import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/*
 * @Description TODO
 * 页面错误配置
 * @Author 顾嘉晟
 * @Date 2021-02-04
 *
 **/
@Configuration
public class ErrorConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.NOT_FOUND, "/500.html");
    }
}
