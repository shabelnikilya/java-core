package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example66_Debounce {
    public static void main(String[] args) throws InterruptedException {
        int[] delays = { 200, 1100, 1000, 200, 1400, 700 };
        Observable.range(0, delays.length)
                .map(i -> delays[i])
                .scan(Integer::sum)
                .flatMap(delay -> Observable.just(delay)
                    .delay(delay, TimeUnit.MILLISECONDS)
                    .doOnNext(System.out::println))
                .debounce(1, TimeUnit.SECONDS)
                .subscribe(p -> System.out.println("debounced "+p));
        Thread.sleep(5000);
    }
}


// РЕЗУЛЬТАТ, если закомментировать LOC 15:
// debounced 200
// debounced 2500
// debounced 4600
