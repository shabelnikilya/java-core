package org.example.rx;

/*
* Шаг 1) Сначала запустим с закомментированным filter(); должны увидеть:
*             One
*             Two
*             Completed
*
* Шаг 2) Теперь раскомментируем filter(), чтобы он повлиял на эмиссию; должны увидеть:
*             Two
*             Completed
*
* Шаг 3) И наконец, закомментируем onComplete(); должны увидеть:
*             Two
* */

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

public class Aux16_CreateUsage {

    public static void main(String[] args) {

        ObservableOnSubscribe<String> source = emitter -> {
            emitter.onNext("One");
            emitter.onNext("Two");
            emitter.onComplete();           // закомментировать этот LOC на Шаге 3
        };

        Observable.create(source)
//                .filter(s -> s.contains("w"))
                .subscribe( v -> System.out.println(v),               // Consumer<? super T> onNext
                            e -> e.printStackTrace(),                 // Consumer<? super Throwable> onError
                            () -> System.out.println("Completed"));   // Action onComplete

        Aux01_CommonUtils.pauseMs(100);

    }
}
