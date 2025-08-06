package org.example.rx;

import io.reactivex.Observable;

public class Example04_Create {
    public static void main(String[] args) {
        Observable<String> locations = Observable.create(location -> {
            location.onNext("Bucharest");
            Thread.sleep(1000);
            location.onNext("Krakow");
            Thread.sleep(1000);
            location.onNext("Moscow");
            Thread.sleep(1000);
            location.onNext("Kiev");
            Thread.sleep(1000);
            location.onNext("Sofia");
            location.onComplete();
        });
        locations.subscribe(s -> System.out.println("Location: " + s));
    }
}