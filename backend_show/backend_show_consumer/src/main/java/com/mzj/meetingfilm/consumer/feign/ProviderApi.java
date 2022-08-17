package com.mzj.meetingfilm.consumer.feign;

import com.mzj.meetingfilm.consumer.feign.lmpl.ProviderFallbackAPI;
import com.mzj.meetingfilm.consumer.feign.vo.UserModel;
import com.mzj.meetingfilm.feignconf.FeignHelloConf;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 参数 :
 * path:表示相同路径
 * name:调用服务的微服务名称，name是application名可以调用对应的服务
 * primary：指定子类优先级,默认值为true(给自己的子类生成一个@Primary的注解)
 * fallback：降级方法
 *
 * 为什么只有接口，为什么可以使用? 自动生成FeignClient实现类，也就是接口实现
 * 固定的url显得有点呆，怎么解决? feign默认集成ribbon，在name中写服务名就可以使用ribbon
 */
@FeignClient(name = "hello-service-provider",
        primary = true,
//        url = "http://localhost:7101",
        path = "provider",
        fallback = ProviderFallbackAPI.class)
public interface ProviderApi {

    @RequestMapping(value = "/sayhello",method = RequestMethod.GET)
    String invokerProviderController(@RequestParam("message")String message);

    @RequestMapping(value = "/{providerId}/sayhello",method = RequestMethod.POST)
    String postTest(@RequestHeader("author") String author,
                    @PathVariable("providerId") String providerId,
                    @RequestBody UserModel json);
}
