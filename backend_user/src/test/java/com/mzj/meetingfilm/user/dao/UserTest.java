package com.mzj.meetingfilm.user.dao;

import com.mzj.meetingfilm.user.BackendUserApplicationTests;
import com.mzj.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.mzj.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import com.mzj.meetingfilm.utils.util.MD5Util;
import org.junit.Test;

import javax.annotation.Resource;

public class UserTest extends BackendUserApplicationTests {

    @Resource
    private MoocBackendUserTMapper userTMapper;

    /**
     * 添加后台运维用户功能
     */
    @Test
    public void add(){
        MoocBackendUserT user = new MoocBackendUserT();
        user.setUserName("admin123");
        user.setUserPwd(MD5Util.encrypt("admin147"));
        user.setUserPhone("15669757091");

        userTMapper.insert(user);
    }

    @Test
    public void select(){

    }
}
