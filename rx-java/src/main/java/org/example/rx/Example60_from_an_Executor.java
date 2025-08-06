package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example60_from_an_Executor {
    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        Scheduler scheduler = Schedulers.from(executor);

        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .subscribeOn(scheduler)
                .subscribe(i -> System.out.println("Received " + i
                        + " on thread " + Thread.currentThread().getName()));
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .subscribeOn(scheduler)
                .subscribe(i -> System.out.println("Received " + i
                        + " on thread " + Thread.currentThread().getName()));
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .subscribeOn(scheduler)
                .subscribe(i -> System.out.println("Received " + i
                        + " on thread " + Thread.currentThread().getName()));
        Observable.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia")
                .subscribeOn(scheduler)
//-----------------------------------------------------------------------------------------------
//                .doFinally(executor::shutdown)    // нужен для прекращения получения заданий
//-----------------------------------------------------------------------------------------------
                .subscribe(i -> System.out.println("Received " + i
                        + " on thread " + Thread.currentThread().getName()));
    }

}