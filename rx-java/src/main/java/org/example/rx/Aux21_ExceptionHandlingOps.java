package org.example.rx;

import io.reactivex.Observable;

public class Aux21_ExceptionHandlingOps {
    public static void main(String[] args) {
        Observable<String> obs = Observable.just("one")
                .flatMap(s -> Observable.fromArray(s.split("")));

        Observable.error(new RuntimeException("MyException"))    // <-- error() вызывает Observable's onError()
                .flatMap(x -> Observable.fromArray("two".split("")))
                .subscribe(System.out::print,
                        e -> System.out.println(e.getMessage())         // печатает: MyException
                );

        Observable.error(new RuntimeException("MyException"))
                .flatMap(y -> Observable.fromArray("two".split("")))
                .onErrorResumeNext(obs)
                .subscribe(System.out::print);                          // печатает: one
        System.out.println();

        Observable.error(new RuntimeException("MyException"))
                .flatMap(z -> Observable.fromArray("two".split("")))
                .onErrorReturnItem("42")
                .subscribe(System.out::print);                          // печатает: 42
    }
}
