package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example63_Time_Based_Buffering {
    public static void main(String[] args) throws InterruptedException {
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .buffer(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        Thread.sleep(5000);
    }
}