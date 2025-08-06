package org.example.rx;

import io.reactivex.Observable;

public class Example45A_Zip {
    public static void main(String[] args) {
        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia");
        Observable<Integer> integers = Observable.range(1,4);
        Observable.zip(locations, integers, (s,i) -> s + "-" + i)
                .subscribe(System.out::println);
    }
}