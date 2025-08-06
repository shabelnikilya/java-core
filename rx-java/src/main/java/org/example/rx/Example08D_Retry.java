package org.example.rx;

import io.reactivex.Observable;

import java.util.Random;

// Возможно, придется перезапуститься несколько раз, пока retry() не потеряет надежду.

public class Example08D_Retry {
    public static void main(String[] args) {
        Random random = new Random();
        Observable<Integer> values = Observable.create(o -> {
            o.onNext(1 / (random.nextBoolean() ? 1 : 0));
        });

        values
            .retry(2)         // пытается не больше 2-х раз, прежде чем махнет рукой и выдаст последнюю ошибку
            .subscribe(v -> System.out.println(v),
                    e -> System.out.println("Error: "+e));

    }
}