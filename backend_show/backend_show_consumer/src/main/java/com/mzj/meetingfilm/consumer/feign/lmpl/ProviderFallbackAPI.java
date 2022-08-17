package com.mzj.meetingfilm.consumer.feign.lmpl;

import com.mzj.meetingfilm.consumer.feign.ProviderApi;
import com.mzj.meetingfilm.consumer.feign.vo.UserModel;
import org.springframework.stereotype.Service;

/**
 * 降级实现
 */
@Service
public class ProviderFallbackAPI implements ProviderApi {


    @Override
    public String invokerProviderController(String message) {
        return "invokerProviderController fallback message="+message;
    }

    @Override
    public String postTest(String author, String providerId, UserModel json) {
        return null;
    }

}
