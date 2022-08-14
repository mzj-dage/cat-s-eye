package com.mzj.meetingfilm.utils.common.vo;

import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import com.mzj.meetingfilm.utils.util.ToolUtils;
import lombok.Data;

/**
 * 分页请求类
 */
@Data
public class BasePageVO extends BaserRequestVO{
    private Integer nowPage =1;
    private Integer pageSize =10;

    @Override
    public void checkParam() throws CommonServiceException {
        //TODO nowPage和pageSize不能为空
        if (nowPage<=0 || pageSize<=0){
            throw new CommonServiceException(404,"nowPage和pageSize不能为空");
        }
    }
}
