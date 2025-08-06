package org.example.rx;

import io.reactivex.Observable;

public class Example07_Subscribe_B {
    public static void main(String[] args) {
        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia");

        locations.map(String::length).filter(l -> l >= 5)
                .subscribe(l -> System.out.println("Length: " + l),
                        Throwable::printStackTrace);

    }
}