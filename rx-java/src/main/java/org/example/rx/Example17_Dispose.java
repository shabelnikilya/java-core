package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Example17_Dispose {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> observable =
                Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable =
                observable.subscribe(l -> System.out.println("Item: " + l));

        Thread.sleep(5000);
        disposable.dispose();
        System.out.println("disposing...");
        Thread.sleep(3000);
    }
}