package org.example.rx;

import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class Aux09_ObservableBlocking {

    public static void main(String[] args) {
        Observable<Integer> obs = Observable.range(1,5);
        Double d2 = obs.filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)        // печатает 2 и 4
                .map(Math::sqrt)
                .delay(100, TimeUnit.MILLISECONDS)
                .blockingLast();         // блокирует конвейер, пока не будет эмиттирован последний элемент
        System.out.println(d2);          // печатает: 2.0

    }
}
