package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Random;

public class Example55_ConcurrentExecution {
    public static void main(String[] args) throws InterruptedException {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .subscribeOn(Schedulers.computation())
                .map(s -> longTask(s))
                .subscribe(System.out::println);
        Observable.range(1, 5)
                .subscribeOn(Schedulers.computation())
                .map(i -> longTask(i))
                .subscribe(System.out::println);
        Thread.sleep(20000);
    }

    public static <T> T longTask(T value) throws InterruptedException {
        Thread.sleep(new Random().nextInt(2000));
        return value;
    }

}