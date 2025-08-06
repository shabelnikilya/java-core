package org.example.rx;

import io.reactivex.Observable;

public class Example01_Just {
    public static void main(String[] args) {

        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia");

        locations.subscribe(s -> System.out.println(s)); // запускает "проталкивание" объектов (эмиссию)

    }
}