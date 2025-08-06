package org.example.rx;

import io.reactivex.Observable;

public class Example33A_groupBy {
    public static void main(String[] args) {
        Observable<String> locations = Observable.create(location -> {
            location.onNext("Bucharest");
            Thread.sleep(2000);
            location.onNext("Krakow");
            Thread.sleep(2000);
            location.onNext("Moscow");
            Thread.sleep(2000);
            location.onNext("Kiev");
            Thread.sleep(2000);
            location.onNext("Sofia");
            //location.onComplete();
        });

        locations.
            groupBy(s -> s.length())
            .subscribe(group -> {
                System.out.println(group.getKey());
                group.subscribe(System.out::println);
            });
    }

}