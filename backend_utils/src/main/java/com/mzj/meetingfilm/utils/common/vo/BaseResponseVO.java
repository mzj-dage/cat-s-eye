package com.mzj.meetingfilm.utils.common.vo;

import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * 表现层公共返回
 */
@Data
public class BaseResponseVO<M> {
    private Integer code; // 业务编号
    private String message; // 异常信息
    private M data;  // 业务数据返回

    //成功但是无参数
    public static BaseResponseVO success(){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setCode(200);
        responseVO.setMessage("");
        responseVO.setData(null);
        return responseVO;
    }
    //成功有参数
    public static<M> BaseResponseVO success(M data){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setCode(200);
        responseVO.setMessage("");
        responseVO.setData(data);
        return responseVO;
    }

    //出现业务异常
    public static<M> BaseResponseVO serviceException(CommonServiceException commonException){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setCode(commonException.getCode());
        responseVO.setMessage(commonException.getErrMessage());
        return responseVO;
    }
}
