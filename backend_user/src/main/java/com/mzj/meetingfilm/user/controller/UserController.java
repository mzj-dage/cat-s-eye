package com.mzj.meetingfilm.user.controller;


import com.mzj.meetingfilm.user.controller.vo.LoginReqVO;
import com.mzj.meetingfilm.user.service.UserServiceApi;
import com.mzj.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import com.mzj.meetingfilm.utils.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceApi userServiceApi;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponseVO login(@RequestBody LoginReqVO reqVO) throws CommonServiceException {

        //数据验证
        reqVO.checkParam();
        String userId = userServiceApi.checkUserLogin(reqVO.getUsername(), reqVO.getPassword());

        JwtTokenUtil tokenUtil = new JwtTokenUtil();
        String randomKey = tokenUtil.getRandomKey();
        String token = tokenUtil.generateToken(userId,randomKey);
        /**
         * createTime
         * 过期时间
         * randomkey - JWT数据签名: AES -> 源数据 + 盐 -> 在token中解析出randomkey -> 数据验签
         * userid - 用户身份验证
         */


        //randomKey token
        Map<String,String> result = new HashMap<>();
        result.put("randomKey",randomKey);
        result.put("token",token);
        return BaseResponseVO.success(result);
    }
}
