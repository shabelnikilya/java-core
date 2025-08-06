package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example46_combineLatest {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> source1 = Observable.interval(1,
                TimeUnit.SECONDS)
                .map(l -> "Source1: " + (l + 1) + " s");

        Observable<String> source2 =
                Observable.interval(400, TimeUnit.MILLISECONDS)
                        .map(l -> "Source2: " + ((l + 1) * 400) + " ms");
        Observable.combineLatest(source1, source2, (s1, s2) -> (s1 + "-" + s2))
                .subscribe(System.out::println);
        Thread.sleep(3000);
    }
}