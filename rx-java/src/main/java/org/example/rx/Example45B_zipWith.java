package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example45B_zipWith {
    public static void main(String[] args) {
        Observable<String> locations = Observable
        .just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia");

        locations.zipWith(
           Observable.interval(500, TimeUnit.MILLISECONDS),
                (item, interval) -> item)
                .blockingSubscribe(System.out::println);
    }
}