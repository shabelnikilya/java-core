package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Aux24_ConditionalAndBooleanOps {
    public static void main(String[] args) {
        Observable<String> obs = Observable.just("one")
                .flatMap(s -> Observable.fromArray(s.split("")));
        Single<Boolean> cont = obs.contains("n");
        System.out.println(cont.blockingGet());                 // печатает: true
        obs.defaultIfEmpty("two")
                .subscribe(System.out::print);                  // печатает: one
        System.out.println();

        Observable.empty().defaultIfEmpty("two")
                .subscribe(System.out::print);                  // печатает: two
        System.out.println();

        Single<Boolean> equal = Observable.sequenceEqual(obs,
                Observable.just("one"));
        System.out.println(equal.blockingGet());                // печатает: false
        equal = Observable.sequenceEqual(Observable.just("one"),
                Observable.just("one"));
        System.out.println(equal.blockingGet());                // печатает: true
        equal = Observable.sequenceEqual(Observable.just("one"),
                Observable.just("two"));
        System.out.println(equal.blockingGet());                // печатает: false
    }
}
