package com.mzj.meetingfilm.apigwzuul.config;

import com.mzj.meetingfilm.apigwzuul.filters.JWTFilter;
import com.mzj.meetingfilm.apigwzuul.filters.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {

    @Bean
    public MyFilter initMyFilter(){
        return new MyFilter();
    }

    @Bean
    public JWTFilter initJWTFilter(){
        return new JWTFilter();
    }
}
