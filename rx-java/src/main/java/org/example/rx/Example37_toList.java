package org.example.rx;

import io.reactivex.Observable;

public class Example37_toList {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .toList()
                .subscribe(list -> System.out.println(list));
    }
}