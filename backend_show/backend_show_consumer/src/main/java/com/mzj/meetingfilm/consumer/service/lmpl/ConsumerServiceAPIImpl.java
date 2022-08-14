package com.mzj.meetingfilm.consumer.service.lmpl;

import com.mzj.meetingfilm.consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ConsumerServiceAPIImpl implements ConsumerServiceAPI {
    @Autowired
    private RestTemplate restTemplate;

    /*@Autowired
    private LoadBalancerClient eurekaClient;*/

    @Override
    public String sayHello(String message) {
        /*// 准备工作(传统的Rest方式)
        String hostname = "localhost";
        int port = 7101;
        String uri = "/provider/sayhello?message="+message;
        //http://localhost:7101/provider/sayhello?message=hello;
        String url = "http://"+hostname+":"+port+uri;*/

        //GET Register方式
//        ServiceInstance serviceInstance = eurekaClient.choose("hello-service-provider");
//        String hostname =serviceInstance.getHost();
//        int port = serviceInstance.getPort();
        String uri = "/provider/sayhello?message="+message;
        String url = "http://hello-service-provider"+uri;


        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}
