package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.stream.Stream;

public class Aux07_ObservableReused {

    public static void main(String[] args) {

        Observable<Double> observable = Observable.range(1, 5)
                .filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)             // печатает 2 и 4 ДВАЖДЫ
                .map(Math::sqrt);

        observable
                .reduce((r, d) -> r + d)
                .subscribe(System.out::println);           // 3.414213562373095

        observable
                .reduce((r, d) -> r + d)
                .map(r -> r / 2)
                .subscribe(System.out::println);           // 1.7071067811865475


// ------ сравним вышеуказанное с поведением Java-стримов ---------------------------------
// ------ (здесь мы ожидаем вылет IllegalStateException):
//        Stream<Integer> ints = Stream.of(1,2,3,4,5);
//        ints.forEach(System.out::println);
//
//        ints.filter(n -> n % 2 == 0)
//            .forEach(System.out::println);

    }
}
