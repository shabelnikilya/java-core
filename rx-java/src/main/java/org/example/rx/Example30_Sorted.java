package org.example.rx;

import io.reactivex.Observable;

public class Example30_Sorted {
    public static void main(String[] args) {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .sorted()
//                .sorted((c1,c2) -> c1.length() - c2.length())
                .subscribe(l -> System.out.println("Location: " + l));
    }

}