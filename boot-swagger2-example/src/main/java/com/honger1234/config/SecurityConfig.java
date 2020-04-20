//package com.honger1234.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @Description: Security配置类
// * @author: zt
// * @date: 2020年3月26日
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig  extends WebSecurityConfigurerAdapter {
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            //普通的接口不需要校验
//            .antMatchers("api/**").permitAll()
//            // swagger页面需要添加登录校验
//            .antMatchers("/swagger-ui.html").authenticated()
//            .and()
//            .formLogin();
//    }
//
//}
