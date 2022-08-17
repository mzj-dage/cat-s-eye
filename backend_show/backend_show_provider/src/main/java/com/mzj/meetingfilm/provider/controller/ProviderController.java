package com.mzj.meetingfilm.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ProviderController {
    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/provider/sayhello",method = RequestMethod.GET)
    public String providerSayHello(String message){

        log.error("provider sayhello port:{},message:{}",port,message);

        return "provider sayhello port:"+port+",message:"+message;
    }

    @RequestMapping(value = "/provider/{providerId}/sayhello",method = RequestMethod.POST)
    public String postTest(
            @RequestHeader("author") String author,
            @PathVariable("providerId") String providerId,
            @RequestBody String json){

        log.error("provider sayhello port:{},message:{},providerId:{},author:{}",port,json,providerId,author);

        return "provider sayhello port:"+port+",message:"+json;
    }
}
