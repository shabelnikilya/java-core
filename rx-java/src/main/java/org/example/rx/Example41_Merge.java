package org.example.rx;

import io.reactivex.Observable;

public class Example41_Merge {
    public static void main(String[] args) {
        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia");
        Observable<String> locations2 =
                Observable.just("Berlin", "Stuttgart", "London");
        Observable.merge(locations, locations2)
                .subscribe(s -> System.out.println("Location: " + s));
    }
}