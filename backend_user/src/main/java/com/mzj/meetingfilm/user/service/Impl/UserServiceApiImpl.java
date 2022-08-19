package com.mzj.meetingfilm.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzj.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.mzj.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import com.mzj.meetingfilm.user.service.UserServiceApi;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import com.mzj.meetingfilm.utils.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceApiImpl implements UserServiceApi {

    @Resource
    private MoocBackendUserTMapper userTMapper;

    @Override
    public String checkUserLogin(String username, String password) throws CommonServiceException {

        //避免数据出现问题
        List<MoocBackendUserT> list = userTMapper.selectPwtByUsername(username);
        MoocBackendUserT user = null;
        if (list!=null && list.size()>0){
            user = list.stream().findFirst().get();
        }else{
            throw new CommonServiceException(404,"The user name was entered incorrectly");// 用户名输入有误
        }
        //验证密码是否正确[密码要做MD5加密,才能验证是否匹配]
        String encrypt = MD5Util.encrypt(password);
        if (!encrypt.equals(user.getUserPwd())){
            throw new CommonServiceException(500,"incorrectly account or password");//用户名或密码错误
        }else{
            return user.getUuid()+"";
        }
    }
}
