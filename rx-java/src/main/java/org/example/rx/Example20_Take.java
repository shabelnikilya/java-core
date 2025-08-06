package org.example.rx;

import io.reactivex.Observable;

public class Example20_Take {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .take(2)
                .subscribe(s -> System.out.println("Location: " + s));
    }

}