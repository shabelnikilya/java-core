package org.example.rx;

import io.reactivex.Observable;

public class Example40_toMultiMap {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .toMultimap(s->s.charAt(0))
                .subscribe(map -> System.out.println(map));
    }
}