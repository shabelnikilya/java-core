package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Example10_HotObservables {
    public static void main(String[] args) throws InterruptedException{
        Observable<Long> myObservable = Observable.interval(1, TimeUnit.SECONDS);

//------------ publish() создает ConnectableObservable из обычного Observable --------------------
        ConnectableObservable<Long> connectableObservable = myObservable.publish();
//------------------------------------------------------------------------------------------------
        connectableObservable.subscribe(item -> System.out.println("Observer 1: " + item));

//------------ connect() нужен, чтобы ConnectableObservable запустил эмиссию ---------------------
        connectableObservable.connect();
//------------------------------------------------------------------------------------------------

        Thread.sleep(3000);
        connectableObservable.subscribe(item -> System.out.println("Observer 2: " + item));
        Thread.sleep(3000);
    }
}
