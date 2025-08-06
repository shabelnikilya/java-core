package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example43_merge_interval {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> source1 = Observable.interval(1,
                TimeUnit.SECONDS)
                .map(l -> "Source1: " + (l + 1) + " s");

        Observable<String> source2 = Observable.interval(500,
                TimeUnit.MILLISECONDS)
                .map(l -> "Source2: " + ((l + 1) * 500) + " ms");

        Observable.merge(source1, source2)
                .subscribe(System.out::println);

        Thread.sleep(5000);
    }
}