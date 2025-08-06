package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import java.util.concurrent.TimeUnit;

public class Aux12_ObservableHot {

    public static void main(String[] args) {
        ConnectableObservable<Long> hot =
                Observable.interval(10, TimeUnit.MILLISECONDS).publish();
        hot.connect();
        hot.subscribe(i -> System.out.println("First: " + i));
        Aux01_CommonUtils.pauseMs(25);
        hot.subscribe(i -> System.out.println("Second: " + i));
        Aux01_CommonUtils.pauseMs(25);
    }
}

//*** ВОЗМОЖНЫЙ РЕЗУЛЬТАТ ***
//  First: 10
//  First: 11
//  First: 12
//  First: 13
//  First: 14
//  Second: 14
//  First: 15
//  Second: 15
//  First: 16
//  Second: 16