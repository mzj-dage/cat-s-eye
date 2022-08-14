package com.mzj.meetingfilm.consumer.config;

import com.netflix.loadbalancer.*;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule iRule(){
        return new RoundRobinRule(); //循环规则
    }

    @Bean
    public IPing iPing(){
        /*return new PingUrl(false,"abc");*/
        return new NIWSDiscoveryPing();
    }
}
