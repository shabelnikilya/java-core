package org.example.rx;

import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class Aux11_ObservableCold {

    public static void main(String[] args) {
        Observable<Long> cold = Observable.interval(1000, TimeUnit.MILLISECONDS);
        cold.subscribe(i -> System.out.println("First: " + i));
        Aux01_CommonUtils.pauseMs(2500);
        cold.subscribe(i -> System.out.println("Second: " + i));
        Aux01_CommonUtils.pauseMs(2500);
    }
}

//*** ВОЗМОЖНЫЙ РЕЗУЛЬТАТ ***
//    First: 0
//    First: 1
//    First: 2
//    Second: 0
//    First: 3
//    Second: 1
//    First: 4


