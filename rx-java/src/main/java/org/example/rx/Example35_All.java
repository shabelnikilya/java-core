package org.example.rx;

import io.reactivex.Observable;

public class Example35_All {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .all(s -> s.length() > 3)
                .subscribe(result -> System.out.println("Result: " + result));

    }

}