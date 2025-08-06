package org.example.rx;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Example75_Observable_to_Flowable {
    public static void main(String[] args) throws InterruptedException {
        Observable.range(1, 1_000_000)
//---------------------------------------------------------------------------
//                .toFlowable(BackpressureStrategy.LATEST)
//---------------------------------------------------------------------------
                .map(Counter::new)
                .observeOn(Schedulers.io())
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
            //System.out.println("Creating " + count);
        }
    }
}