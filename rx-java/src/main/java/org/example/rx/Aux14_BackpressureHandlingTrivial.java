package org.example.rx;

import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

public class Aux14_BackpressureHandlingTrivial {

    public static void main(String[] args) {

        PublishProcessor<Integer> hot = PublishProcessor.create();
        hot.onBackpressureDrop(v -> System.out.println("Dropped: "+ v))
                .observeOn(Schedulers.io(), true)
                .subscribe(System.out::println, Throwable::printStackTrace);
        for (int i = 0; i < 1_000_000; i++) {
            hot.onNext(i);
        }

    }
}
