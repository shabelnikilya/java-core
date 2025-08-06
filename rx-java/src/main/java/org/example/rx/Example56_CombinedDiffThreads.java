package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Random;

public class Example56_CombinedDiffThreads {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> locations =
                Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                          .subscribeOn(Schedulers.computation())
                          .map(s -> longTask((s)));
        Observable<Integer> integers =
                Observable.range(1, 5)
                          .subscribeOn(Schedulers.computation())
                          .map(i -> longTask((i)));

        Observable.zip(locations, integers, (s,i) -> s + "-" + i)
                .subscribe(System.out::println);
        Thread.sleep(10000);
    }

    public static <T> T longTask(T value) throws InterruptedException {
        Thread.sleep(new Random().nextInt(2000));
        return value;
    }

}