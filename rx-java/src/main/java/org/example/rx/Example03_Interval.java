package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example03_Interval {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> steps = Observable.interval(1, TimeUnit.SECONDS);
        steps.subscribe(l -> System.out.println(l));


//----- т.к. thread является демоническим, JVM остановится сразу, если не положить main() спать.
//        steps.subscribe(l -> System.out.println(Thread.currentThread().isDaemon() + " : " + l));    // true : <0, etc.>


        Thread.sleep(10500);


//----- альтернативный подход: вызвать blockingSubscribe() (см. Example33B_Timestamp)



    }
}