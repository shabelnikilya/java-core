package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public class Aux23_UtilityOps {
    public static void main(String[] args) {
        Observable<String> obs = Observable.just("one")
                .flatMap(s -> Observable.fromArray(s.split("")));
        obs.delay(5, TimeUnit.MILLISECONDS)
                .subscribe(System.out::print);                          // печатает: one
        Aux01_CommonUtils.pauseMs(50);
        System.out.println();

        Observable source = Observable.range(1,5);
        Disposable disposable = source.subscribe();
        Observable.using(
                    () -> disposable,
                    x -> source,
                    y -> System.out.println("Resource was " + y))       // печатает: Resource was DISPOSED
                .delay(10, TimeUnit.MILLISECONDS)
                .subscribe(System.out::print);                          // печатает: 12345
        Aux01_CommonUtils.pauseMs(25);
    }
}
