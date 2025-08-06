package org.example.rx;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscription;

public class Example70_Flowable {
    public static void main(String[] args) throws InterruptedException {
        // Observable будет класть элементы в буфер, пока вся память не кончится,
        // а вот Flowable реализует стратегию противодавления (по умолчанию, буферизация)
        Flowable.range(1, 1_000_000)
                .map(Counter::new)
                .observeOn(Schedulers.computation())
                .subscribe(counter -> {
                    Thread.sleep(30);
                    System.out.println("Received " + counter.count);
                }, Throwable::printStackTrace);
        Thread.sleep(20000);

    }

    static final class Counter {
        int count;

        Counter(int count) {
            this.count = count;
            System.out.println("Creating " + count);
        }
    }
}