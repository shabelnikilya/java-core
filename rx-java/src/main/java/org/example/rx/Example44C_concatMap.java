package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example44C_concatMap {

    static long delay = 1000;

    public static Observable<String> remoteRequest(String s) {
        delay -= 100;
        return Observable.just("("+s+")("+delay+")")
                .delay(delay, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia");
        locations.concatMap(s -> remoteRequest(s))
                .blockingSubscribe(x -> System.out.println(x));
    }
}