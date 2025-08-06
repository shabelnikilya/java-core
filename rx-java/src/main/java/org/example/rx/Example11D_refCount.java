package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Example11D_refCount {
    public static void main(String[] args) throws InterruptedException {
        ConnectableObservable<Long> obs =
                Observable.interval(1,
                    TimeUnit.SECONDS)
                    .doOnNext(v->
                            System.out.println("Generated: "+v))
                    .publish();
        Thread.sleep(3000);
        Observable<Long> refCountObs = obs.refCount();
        Thread.sleep(3000);
        Disposable sub1 = refCountObs.subscribe(s ->
                System.out.println("Observer 1: " + s));
        Thread.sleep(3000);
        Disposable sub2 = refCountObs.subscribe(l ->
                System.out.println("Observer 2: " + l));

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