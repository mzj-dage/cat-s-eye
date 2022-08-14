package com.mzj.meetingfilm.showhystrix.observe;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class ObserveCommandDemo extends HystrixObservableCommand<String> {

    private String name;

    public ObserveCommandDemo(String name){
        super(Setter
                .withGroupKey(HystrixCommandGroupKey
                        .Factory.asKey("ObserveCommandDemo"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("ObservableCommandKey")));
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        System.err.println("current Thread:"+Thread.currentThread().getName());
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                // 业务处理(可以有无数个onNext,每一个onNext就是一次业务处理)
                subscriber.onNext("action 1 , name="+name);
                subscriber.onNext("action 2 , name="+name);
                subscriber.onNext("action 3 , name="+name);

                // 业务处理结束
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }
}
