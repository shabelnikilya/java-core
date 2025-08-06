package org.example.rx;

import io.reactivex.Observable;

public class Aux22_LifeCycleHandlingOps {
    static int tally = 0;
    static String res;
    public static void main(String[] args) {
        Observable.range(1, 10)
                .doOnSubscribe(item -> System.out.println("Subscibed and received " + item))
                .doOnNext(item -> {tally += item;
//                  throw new RuntimeException("I'm broken!"); // снять коммент и запустить; потом перевернуть
//                                                             // комменты на строках 18 and 19
                })
                .doOnError(System.out::println)
                .doOnComplete(() -> res = "Done!")
                .subscribe(x -> System.out.println("another onNext event handler fired!"),
                        Throwable::printStackTrace,
//                        x -> System.out.println("Error swallowed!"),
                        () -> System.out.println("another onComplete event handler fired!"));

        assert tally == 55;
        assert res.equals("Done!");

        System.out.println("Life cycle events handled successfully!");

    }
}
