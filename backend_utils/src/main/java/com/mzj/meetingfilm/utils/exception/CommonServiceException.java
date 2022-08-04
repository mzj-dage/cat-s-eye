package com.mzj.meetingfilm.utils.exception;

import lombok.Data;

/**
 * 公共的业务逻辑错误
 */
@Data
public class CommonServiceException extends Exception{
    private Integer code;
    private String errMessage;

    public CommonServiceException(Integer code,String errMessage){
        this.code = code;
        this.errMessage = errMessage;
    }
}
