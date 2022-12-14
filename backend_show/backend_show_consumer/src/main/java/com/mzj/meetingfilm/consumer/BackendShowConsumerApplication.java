package com.mzj.meetingfilm.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients  // 开启Feign环境支持
@EnableDiscoveryClient  //DiscoveryClient可以集成大部分的注册中心
@SpringBootApplication  //而EnableEurekaClient只对Eureka使用
public class BackendShowConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendShowConsumerApplication.class, args);
    }

}
