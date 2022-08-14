package com.mzj.meetingfilm.showhystrix.observe;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

public class ObservableCommandTest {

    @Test
    public void observableCommand(){
        ObserveCommandDemo commandDemo = new ObserveCommandDemo("observableCommand");
        Observable<String> observe = commandDemo.observe();

        // 非阻塞式调用
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }
}
