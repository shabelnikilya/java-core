package org.example.rx;

import io.reactivex.Observable;

public class Example27_Map {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .map(s->s.length())
                .subscribe(l -> System.out.println("Length: " + l));
    }

}