package com.mzj.meetingfilm.utils.exception;

import com.mzj.meetingfilm.utils.common.vo.BaseResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共异常处理
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(CommonServiceException.class) //处理被@RequestMapping注解抛出的异常
    @ResponseBody
    public BaseResponseVO serviceExceptionHandler(
            HttpServletRequest request, CommonServiceException e){
        log.error("CommonServiceException,reason:{}",e);
        return BaseResponseVO.serviceException(e);
    }
}
