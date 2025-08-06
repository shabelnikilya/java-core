package org.example.rx;

import io.reactivex.Observable;

public class Example07_Subscribe_A {
    public static void main(String[] args) {
        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia");

        locations
                .doOnNext(s -> System.out.println("next: "+s))
                .doOnComplete(() -> System.out.println("complete: "))
                .map(String::length).filter(l -> l >= 5)
                .subscribe(l -> System.out.println("Length: " + l),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done."));

    }
}