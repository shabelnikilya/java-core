package org.example.rx;

import io.reactivex.Observable;

public class Aux08_ObservableCached {
    public static void main(String[] args) {
        Observable<Double> observable = Observable.range(1, 5)
                .filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)             // печатает 2 и 4 ОДИН РАЗ
                .map(Math::sqrt)
                .cache();

        observable
                .reduce((r, d) -> r + d)
                .subscribe(System.out::println);           // 3.414213562373095

        observable
                .reduce((r, d) -> r + d)
                .map(r -> r / 2)
                .subscribe(System.out::println);           // 1.7071067811865475

    }
}
