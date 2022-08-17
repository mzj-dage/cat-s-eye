package com.mzj.meetingfilm.apis.film;

import com.mzj.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mzj.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * feign提供的公共接口服务
 */
public interface FilmFeignApis {

    /**
     * 对外暴露的接口服务
     */
    // 根据电影主键获取电影信息
    @RequestMapping(value = "/films/{filmId}",method = RequestMethod.GET)
    BaseResponseVO<DescribeFilmRespVO> describeFilmById(@PathVariable("filmId")String filmId) throws CommonServiceException;
}
