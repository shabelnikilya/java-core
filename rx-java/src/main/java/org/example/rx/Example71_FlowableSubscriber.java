package org.example.rx;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Example71_FlowableSubscriber {
    public static void main(String[] args) throws InterruptedException {
        Flowable.range(1, 1_000_000)
                .map(Counter::new)
//                .observeOn(Schedulers.io())  // снять коммент, чтобы увидеть работу нескольких thread'ов
                .subscribe(new Subscriber<Counter>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        s.request(10);
                    }

                    @Override
                    public void onNext(Counter counter) {
                        System.out.println(counter.count);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    static final class Counter {
        int count;
        Counter(int count) {
            this.count = count;
            System.out.println("Creating " + count);
        }
    }
}