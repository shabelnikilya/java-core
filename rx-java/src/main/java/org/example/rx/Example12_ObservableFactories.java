package org.example.rx;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Example12_ObservableFactories {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Observable.just() ===");

        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev",
                        "Sofia").subscribe(s -> System.out.println(s));

        System.out.println("=== Observable.create() ===");
        Observable.create(location -> {
            location.onNext("Bucharest");
            location.onNext("Krakow");
            location.onNext("Moscow");
            location.onNext("Kiev");
            location.onNext("Sofia");
            location.onComplete();
        }).subscribe(s -> System.out.println("Location: " + s));

        System.out.println("=== Observable.interval() ===");
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println("RxJava: " + s));
        Thread.sleep(5000);

        System.out.println("=== Observable.fromIterable() ===");
        Observable.fromIterable(new ArrayList<String> (Arrays.asList("Bucharest", "Krakow", "Moscow", "Kiev",
                "Sofia"))).subscribe(s -> System.out.println("Location: " + s));

        System.out.println("=== Observable.range() ===");
        Observable.range(3, 4)
                .subscribe(s -> System.out.println("Counter: " + s));

        System.out.println("=== Observable.empty() ===");
        Observable.empty().subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done from empty."));

        System.out.println("=== Observable.never() ===");
        Observable.never().subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done from never."));
        Thread.sleep(5000);

        System.out.println("=== Observable.error() ===");
        Observable.error(new Exception("Forcing an error!"))
                .subscribe(s -> System.out.println("Received: " + s),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done from error."));
    }
}