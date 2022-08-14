package com.mzj.meetingfilm.showhystrix.command;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class CommandDemo extends HystrixCommand<String> {

    private String name;

    public CommandDemo(String name){
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter()
                        .withCircuitBreakerEnabled(false) //false表示将请求缓存关闭 true表示将请求缓存开启
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                ).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("myThreadPool")));
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 单次请求调用的业务方法
    @Override
    protected String run() throws Exception {
        String result = "CommandHelloWorld name : "+name;
        System.err.println(result+", currentThread-"+Thread.currentThread().getName());
        return result;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }
}
