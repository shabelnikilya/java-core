package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Example18_CompositeDisposable {
    private static final CompositeDisposable disposables
            = new CompositeDisposable();

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> observable =
                Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable1 =
                observable.subscribe(l -> System.out.println("Observer 1: " + l));
        Disposable disposable2 =
                observable.subscribe(l -> System.out.println("Observer 2: " + l));
        disposables.addAll(disposable1, disposable2);
        Thread.sleep(5000);
        disposables.dispose();
        System.out.println("disposing all...");
        Thread.sleep(5000);
    }

}