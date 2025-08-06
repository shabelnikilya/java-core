package org.example.rx;

import io.reactivex.Observable;

public class Example21_Skip {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .skip(2)
                .subscribe(s -> System.out.println("Location: " + s));
    }

}