package com.mzj.meetingfilm.cinema.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mzj.meetingfilm.cinema.controller.vo.CinemaSaveReqVO;
import com.mzj.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.mzj.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.mzj.meetingfilm.cinema.service.CinemaServiceAPI;
import com.mzj.meetingfilm.utils.common.vo.BasePageVO;
import com.mzj.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 影院模块表现层
 */
@RestController
@RequestMapping(value = "/cinemas")
public class CinemaController {
    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    @RequestMapping(value = "cinema:add",method = RequestMethod.POST)
    public BaseResponseVO saveCinema(@RequestBody CinemaSaveReqVO cinemaSaveReqVO) throws CommonServiceException {
        //入参校验
        cinemaSaveReqVO.checkParam();

        cinemaServiceAPI.saveCinema(cinemaSaveReqVO);

        return BaseResponseVO.success();
    }

    /**
     * fallback是业务处理的保底方案，尽可能保证这个保底方案一定能成功
     */
    public BaseResponseVO fallbackMethod(BasePageVO basePageVO) throws CommonServiceException{

        // describeCinemas->> 获取影院列表 -> 在redis查询影院列表[redis挂了,或者数据没了]

        // fallback里捕获到超时或者数据内容与分页数不一致

        // fallback就在数据库中查询真实的影院信息

        // 返回一定是成功,或者业务处理失败
        return BaseResponseVO.success();
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
            },ignoreExceptions = CommonServiceException.class)
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO describeCinemas(BasePageVO basePageVO) throws CommonServiceException {

        IPage<MoocCinemaT> cinemasRespVOIPage = cinemaServiceAPI.describeCinemas(basePageVO.getNowPage(), basePageVO.getPageSize());

        if (basePageVO.getNowPage()>10000){
            throw new CommonServiceException(400,"nowPage太大了,不支持处理");
        }

        Map<String, Object> cinema = descrbePageResult(cinemasRespVOIPage, "cinema");

        return BaseResponseVO.success(cinema);
    }

    // 封装的分页返回方法
    private Map<String,Object> descrbePageResult(IPage page,String title){
        Map<String,Object> result = Maps.newHashMap();

        result.put("totalSize",page.getTotal());
        result.put("totalPages",page.getPages());
        result.put("pageSize",page.getSize());
        result.put("nowPage",page.getCurrent());

        result.put(title, page.getRecords());

        return result;
    }
}
