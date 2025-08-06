package org.example.rx;

import io.reactivex.Observable;

public class Example22_takeWhile {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .takeWhile(s -> s.length() >=5)
                .subscribe(s -> System.out.println("Location: " + s));
    }

}