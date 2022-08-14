package com.mzj.meetingfilm.showhystrix.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CommandTest {

    @Test
    public void executeTest(){
        long begin = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("execute");
        //同步执行Command(从宏观上来看多个线程同时向前推进)
        String result = commandDemo.execute();
//        commandDemo.run(); 直接执行run，代表前面的步骤已经被执行了

        long end = System.currentTimeMillis();
        System.out.println("result="+result+", 耗费时间为"+(end-begin)+"毫秒");
    }

    @Test
    public void queueTest() throws ExecutionException, InterruptedException {
        long begin = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("queue");

        // 异步执行(一个一个的执行线程)，队列的特性就是先进先出
        Future<String> queue = commandDemo.queue();
        String result = queue.get();

        long end = System.currentTimeMillis();
        System.out.println("result="+result+", 耗费时间为"+(end-begin)+"毫秒");
    }

    @Test
    public void observeTest(){
        long begin = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("observe");

        //阻塞式调用
        Observable<String> observe = commandDemo.observe();
        String result = observe.toBlocking().single();

        long end = System.currentTimeMillis();
        System.out.println("result="+result+", 耗费时间为"+(end-begin)+"毫秒");

        //非阻塞式调用
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("observe , onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("observe , onError - throwable="+throwable);
            }

            @Override
            public void onNext(String result) {
                System.err.println("observe , onNext");
            }
        });
    }

    @Test
    public void toObserveTest(){
        long begin = System.currentTimeMillis();
        CommandDemo observe = new CommandDemo("observe");
        Observable<String> observable = observe.toObservable();
        //阻塞式调用
        String result = observable.toBlocking().single();
        long end = System.currentTimeMillis();
        System.out.println("当前的result为:"+result+", 当前耗费时间:"+(end-begin));

        //非阻塞式调用
        Observable<String> observableTwo = observe.toObservable();
        observableTwo.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted正在执行");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.err.println("onNext正在执行"+s);
            }
        });
    }

    //演示请求缓存
    @Test
    public void requestCache() throws ExecutionException, InterruptedException {
        // 开启请求上下文
        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();
        long begin = System.currentTimeMillis();

        CommandDemo c1 = new CommandDemo("c1");
        CommandDemo c2 = new CommandDemo("c2");
        CommandDemo c3 = new CommandDemo("c1");

        //第一次请求
        String String1 = c1.execute();
        System.out.println("result="+String1+", 耗费时间为"+(System.currentTimeMillis()-begin)+"毫秒");

        //第二次请求
        String String2 = c2.execute();
        System.out.println("result="+String2+", 耗费时间为"+(System.currentTimeMillis()-begin)+"毫秒");

        //第三次请求
        String String3 = c3.execute();
        System.out.println("result="+String3+", 耗费时间为"+(System.currentTimeMillis()-begin)+"毫秒");

        // 请求上下文关闭
        requestContext.close();
    }
}
