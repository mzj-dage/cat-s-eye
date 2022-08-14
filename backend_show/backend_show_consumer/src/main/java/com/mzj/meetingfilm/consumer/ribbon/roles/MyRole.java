package com.mzj.meetingfilm.consumer.ribbon.roles;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

public class MyRole extends AbstractLoadBalancerRule {

    public MyRole(){}

    @Override
    public Server choose(Object key) {
        // 无论传入什么，都返回null
        return null;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }
}
