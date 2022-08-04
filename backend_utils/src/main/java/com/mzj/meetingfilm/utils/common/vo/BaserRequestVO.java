package com.mzj.meetingfilm.utils.common.vo;

import com.mzj.meetingfilm.utils.exception.CommonServiceException;

/**
 * 公共的请求对象
 */
public abstract class BaserRequestVO {

    //公共的参数验证方法
    public abstract void checkParam() throws CommonServiceException;
}
