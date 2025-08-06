package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Example11A_ColdSleep {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> obs =
            Observable.interval(1,
                TimeUnit.SECONDS)
                .doOnNext(v->
                    System.out.println("Generated: "+v));
        System.out.println("Observable created, no subscriptions...");
        Thread.sleep(3000);

        Disposable sub1 = obs.subscribe(s ->
                System.out.println("Observer 1: " + s));
        System.out.println("Observer 1 subscribed...");

        Thread.sleep(3000);
        Disposable sub2 = obs.subscribe(l ->
                System.out.println("Observer 2: " + l));
        System.out.println("Observer 2 subscribed...");

        Thread.sleep(3000);
        sub1.dispose();
        System.out.println("Observer 1 disposed...");

        Thread.sleep(3000);
        sub2.dispose();
        System.out.println("Observer 2 disposed...");

        Thread.sleep(3000);
        System.out.println("Finished.");

    }
}