package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Random;

public class Example61_multiple_Observers {
    public static void main(String[] args) throws InterruptedException {
        Observable locations = Observable.range(1, 5)
                          .subscribeOn(Schedulers.computation());

        locations.subscribe(i->System.out.println(i + " on thread " + Thread.currentThread().getName()));
        locations.subscribe(i->System.out.println(i + " on thread " + Thread.currentThread().getName()));
        Thread.sleep(1000);
    }
}