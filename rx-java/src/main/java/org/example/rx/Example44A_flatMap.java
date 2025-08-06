package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example44A_flatMap {
    public static Observable<String> remoteRequest(String s){
        return Observable.just( "(" + s + ")" );
    }

    public static void main(String[] args) {
        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia");
        locations.flatMap(s -> remoteRequest(s))
                .subscribe(System.out::println);
    }
}