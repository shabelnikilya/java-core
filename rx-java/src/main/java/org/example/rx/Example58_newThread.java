package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Example58_newThread {
    public static void main(String[] args) throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS,
                Schedulers.single())
                .subscribe(i -> System.out.println("Received " + i
                        + " on thread " + Thread.currentThread().getName()));
        System.out.println("main thread");

        Thread.sleep(5000);
    }

}