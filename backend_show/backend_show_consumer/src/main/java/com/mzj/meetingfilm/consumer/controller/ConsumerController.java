package com.mzj.meetingfilm.consumer.controller;

import com.mzj.meetingfilm.consumer.feign.ProviderApi;
import com.mzj.meetingfilm.consumer.feign.vo.UserModel;
import com.mzj.meetingfilm.consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerServiceAPI serviceAPI;

    @Resource
    private ProviderApi providerApi;

    @RequestMapping(value = "/sayhello/Film")
    public String sayHelloFilm(String message){
        System.out.println(message);
        return providerApi.invokerProviderController(message);
    }

    @RequestMapping(value = "/sayhello/post")
    public String sayHelloPost(String author, String providerId, UserModel userModel){
        System.out.println(userModel.toString());
        return providerApi.postTest(author, providerId, userModel);
    }


    @RequestMapping(value = "/sayhello")
    public String sayHello(String message){

        return serviceAPI.sayHello(message);
    }
}
