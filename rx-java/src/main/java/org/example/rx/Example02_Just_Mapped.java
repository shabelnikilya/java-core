package org.example.rx;

import io.reactivex.Observable;

public class Example02_Just_Mapped {
    public static void main(String[] args) {
        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev",
                        "Sofia");
        locations.map(s -> s.length())
                 .subscribe(l -> System.out.println(l));
    }
}