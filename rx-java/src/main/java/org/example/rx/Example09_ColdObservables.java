package org.example.rx;

import io.reactivex.Observable;

public class Example09_ColdObservables {
    public static void main(String[] args) {
        Observable<String> locations = Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia");
        locations.subscribe(s -> System.out.println("Location for observer 1: " + s));
        locations.subscribe(s -> System.out.println("Location for observer 2: " + s));
    }
}