package com.mzj.meetingfilm.showhystrix.commandcollasper;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 请求合并测试
 */
public class CommandCollapserTest {

    @Test
    public void collapserTest() throws ExecutionException, InterruptedException {
        // 开启请求上下文
        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();

        // 构建请求
        CommandCollapser c1 = new CommandCollapser(1);
        CommandCollapser c2 = new CommandCollapser(2);
        CommandCollapser c3 = new CommandCollapser(3);
        CommandCollapser c4 = new CommandCollapser(4);

        // 获取结果
        Future<String> q1 = c1.queue();
        Future<String> q2 = c2.queue();
        Future<String> q3 = c3.queue();
        Future<String> q4 = c4.queue();

        // 打印
        String r1 = q1.get();
        String r2 = q2.get();
        String r3 = q3.get();
        String r4 = q4.get();
        System.err.println(r1+","+r2+","+r3+","+r4);
        requestContext.close(); // 关闭请求上下文
    }
}
