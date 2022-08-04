package com.mzj.meetingfilm.user.controller.vo;

import com.mzj.meetingfilm.utils.common.vo.BaserRequestVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import com.mzj.meetingfilm.utils.util.ToolUtils;
import lombok.Data;

/**
 * 登录请求对象
 */
@Data
public class LoginReqVO extends BaserRequestVO {
    private String username;
    private String password;

    @Override
    public void checkParam() throws CommonServiceException {
        //TODO 验证数据合法性
        if (ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password)){
            throw new CommonServiceException(404,"username or password must be required！");
        }
    }
}
