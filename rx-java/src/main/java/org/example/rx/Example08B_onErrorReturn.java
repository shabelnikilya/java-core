package org.example.rx;

import io.reactivex.Completable;
import io.reactivex.Observable;

import java.io.IOException;

public class Example08B_onErrorReturn {
    public static void main(String[] args) {
        Observable<Integer> values = Observable.create(o -> {
            o.onNext(1);
            o.onNext(2);
            o.onError(new Exception("some error"));
            o.onNext(3);
            o.onNext(4);
        });

        values
          .onErrorReturn(e -> 666)
          .subscribe(v -> System.out.println(v),
                  e -> System.out.println(e.getMessage()));

    }
}