package com.mzj.meetingfilm.hall.apis;

import com.mzj.meetingfilm.apis.film.FilmFeignApis;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 疑问: 1.为什么Feign需要通过继承接口进行使用服务
 */
@FeignClient(name = "film-service")
public interface FilmFeignApi extends FilmFeignApis {
}