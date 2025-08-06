package org.example.rx;

import io.reactivex.Observable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/*
* Сначала запустим с закомментированным try-catch (и увидим пустой List),
* затем встроим задержку в пределах 200 мсек, раскомментировав try-catch.
* */

public class Aux10_ObservableNonBlocking {
    public static void main(String[] args) {

        // Использует List для захвата результата, потому что лямбды
        // не могут работать с нефинальными локальными переменными:
        List<Double> list = new ArrayList<>();

        Observable<Integer> obs = Observable.range(1,5);
             obs.filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)    // печатает 2 и 4
                .map(Math::sqrt)
                .delay(100, TimeUnit.MILLISECONDS)
                .subscribe(d -> {
                    list.clear();                 // мы хотим лишь последний элемент
                    list.add(d);
                });

        //***************************************************************************************************
//        try {
//            TimeUnit.MILLISECONDS.sleep(200);   // надо минимум 200 мсек, т.к. эмиссия идет каждые 100 мсек
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //***************************************************************************************************

        System.out.println(list);                 // печатает []
                                                  // или [2.0] с раскомментированным try-catch

    }
}
