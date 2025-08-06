package org.example.rx;

import io.reactivex.Observable;

public class Example25_distinctUntilChanged {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Krakow", "Moscow", "Moscow", "Kiev", "Moscow", "Sofia")
                .distinctUntilChanged()
                .subscribe(s -> System.out.println("Location: " + s));
    }

}