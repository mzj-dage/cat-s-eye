package com.mzj.meetingfilm.consumer.feign.lmpl;

import com.mzj.meetingfilm.consumer.feign.ProviderApi;
import com.mzj.meetingfilm.consumer.feign.vo.UserModel;

public class ProviderApiImpl implements ProviderApi {
    @Override
    public String invokerProviderController(String message) {
        return null;
    }

    @Override
    public String postTest(String author, String providerId, UserModel json) {
        return null;
    }
}
