package com.mzj.meetingfilm.feignconf;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class FeignHelloConf {
    @Bean
    public Contract contract(){
        return new feign.Contract.Default(); // 默认的方式
    }
}
