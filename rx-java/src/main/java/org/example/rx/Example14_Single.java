package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Example14_Single {

    public static void main(String[] args) {
        Single.just("Single")
                .map(String::length)
                .subscribe(System.out::println,
                        Throwable::printStackTrace);

        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev",
                        "Sofia");
        locations.first("Default value.")
                .subscribe(System.out::println);
    }
}