package org.example.rx;

/*
* Сначала запустим с закомментированным disposable.dispose(): это заполнит List.
* Затем раскомментируем и увидим, что List окажется пустым;
* это потому, что вызов disposable.dispose() остановит обработку:
* даже с 200-миллисекундной задержкой List не будет заполняться.
* */

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Aux15_DisposableDemo {
    public static void main(String[] args) {
        Observable<Integer> obs = Observable.range(1,5);
        List<Double> list = new ArrayList<>();
        Disposable disposable =
                obs.filter(i -> i % 2 == 0)
                   .doOnNext(System.out::println)      // печатает 2 и 4
                   .map(Math::sqrt)
                   .delay(100, TimeUnit.MILLISECONDS)
                   .subscribe(d -> {
                                    list.clear();
                                    list.add(d);
                                });
        System.out.println(list);                      // печатает: []
        System.out.println(disposable.isDisposed());   // печатает: false

//*** раскомментировать, чтобы отменить обработку и утилизировать конвейер *****************
//        disposable.dispose();
//******************************************************************************************

        try {
             TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }

        System.out.println(disposable.isDisposed());   // печатает: true
        System.out.println(list);                      // печатает: либо [2.0], либо [] при вызове dispose()
    }
}
