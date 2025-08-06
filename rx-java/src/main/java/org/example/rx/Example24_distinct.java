package org.example.rx;

import io.reactivex.Observable;

public class Example24_distinct {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Krakow", "Moscow", "Kiev", "Sofia")
                .distinct()
                .subscribe(s -> System.out.println("Location: " + s));
    }

}