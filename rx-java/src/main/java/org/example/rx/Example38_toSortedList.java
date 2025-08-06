package org.example.rx;

import io.reactivex.Observable;

public class Example38_toSortedList {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .toSortedList()
                .subscribe(list -> System.out.println(list));
    }
}