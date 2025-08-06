package org.example.rx;

/*
* Дополнительные примеры можно найти здесь: https://github.com/ReactiveX/RxJava/wiki/Creating-Observables
* */

import io.reactivex.Observable;
import java.util.concurrent.*;
import java.util.*;

public class Aux20_ConvertingFromXXXOps {
    static class Worker implements Callable<String> {
        int id = 0;
        public Worker(int id) {
            this.id = id;
        }
        @Override
        public String call() throws Exception {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
            return Thread.currentThread().getName() + ", id = " + id;
        }
    }

    public static void main(String[] args) {
        Observable<String> obsFA = Observable.fromArray("one", "two");
        obsFA.subscribe(System.out::println, System.out::println, () -> System.out.println("--- fromArray completed! ---"));

        Observable<String> obsFC = Observable.fromCallable(() -> "I'm from a Callable!");
        obsFC.subscribe(System.out::println, System.out::println, () -> System.out.println("--- fromCallable completed! ---"));

        Observable<Integer> obsFI = Observable.fromIterable(Arrays.asList(1, 2, 3, 4, 5));
        obsFI.subscribe(System.out::println, System.out::println, () -> System.out.println("--- fromIterable completed! ---"));

        ExecutorService exec = Executors.newFixedThreadPool(2);
        Future<String> fut = null;
        for (int i = 1; i <= 5; i++) { fut = exec.submit(new Worker(i)); }
        Observable<String> obsFF = Observable.fromFuture(fut);
        obsFF.subscribe(System.out::println, System.out::println, () -> System.out.println("--- fromFuture completed! ---"));
        exec.shutdown();
    }
}
