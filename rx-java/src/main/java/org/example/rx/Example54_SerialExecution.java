package org.example.rx;

import io.reactivex.Observable;

import java.util.Random;

public class Example54_SerialExecution {
    public static void main(String[] args) throws InterruptedException {
        Observable.just("Bucharest", "Krakow",
                "Moscow", "Kiev", "Sofia")
                .map(s -> longTask(s))
                .subscribe(System.out::println);
        Observable.range(1, 5)
                .map(i -> longTask(i))
                .subscribe(System.out::println);
    }

    public static <T> T longTask(T value) throws InterruptedException {
        Thread.sleep(new Random().nextInt(2000));
        return value;
    }

}