package com.mzj.meetingfilm.user.service;

import com.mzj.meetingfilm.utils.exception.CommonServiceException;
/**
 * 用户接口
 */
public interface UserServiceApi {

    String checkUserLogin(String username,String password) throws CommonServiceException;
}
