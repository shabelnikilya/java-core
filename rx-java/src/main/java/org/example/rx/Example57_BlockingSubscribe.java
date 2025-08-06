package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Random;

public class Example57_BlockingSubscribe {
    public static void main(String[] args) throws InterruptedException {
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                          .subscribeOn(Schedulers.computation())
                          .map(s -> longTask((s)))
                          .blockingSubscribe(System.out::println);
    }

    public static <T> T longTask(T value) throws InterruptedException {
        Thread.sleep(new Random().nextInt(2000));
        return value;
    }

}