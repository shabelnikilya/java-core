package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Example11C_Connect {
    public static void main(String[] args) throws InterruptedException {
        ConnectableObservable<Long> obs =
                Observable.interval(1,
                        TimeUnit.SECONDS)
                        .doOnNext(v-> System.out.println("Generated: "+v))
                        .publish();
        Disposable sub1 = obs.subscribe(s ->
                System.out.println("Observer 1: " + s));
        Disposable sub2 = obs.subscribe(l ->
                System.out.println("Observer 2: " + l));

        System.out.println("Subscribed but not connected...");
        Thread.sleep(3000);
        obs.connect();
        System.out.println("Connected...");
        Thread.sleep(3000);
        sub1.dispose();
        System.out.println("Observer 1 disposed...");
        Thread.sleep(3000);
        sub2.dispose();
        System.out.println("Observer 2 disposed...");
        Thread.sleep(3000);
        System.out.println("Finished...");

    }
}