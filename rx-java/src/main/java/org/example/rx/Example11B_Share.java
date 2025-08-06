package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Example11B_Share {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> obs =
                Observable.interval(1,
                    TimeUnit.SECONDS)
                    .doOnNext(v->
                            System.out.println("Generated: "+v))
                    .share();

        Disposable sub1 = obs.subscribe(s ->
                System.out.println("Observer 1: " + s));
        Thread.sleep(3000);
        Disposable sub2 = obs.subscribe(l ->
                System.out.println("Observer 2: " + l));

        Thread.sleep(2000);
        sub1.dispose();
        System.out.println("Observer 1 disposed...");
        Thread.sleep(2000);
        sub2.dispose();
        System.out.println("Observer 2 disposed...");
        Thread.sleep(2000);
        System.out.println("Finished.");

    }
}