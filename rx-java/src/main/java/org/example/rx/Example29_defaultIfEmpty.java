package org.example.rx;

import io.reactivex.Observable;

public class Example29_defaultIfEmpty {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .filter(s -> s.startsWith("A"))
                .defaultIfEmpty("None")
                .subscribe(l -> System.out.println("Location: " + l));
    }

}