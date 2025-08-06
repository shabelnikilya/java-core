package org.example.rx;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import reactor.core.publisher.BufferOverflowStrategy;

public class Example72_Backpressure_Buffer {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("rx2.buffer-size", "16");
        Flowable.range(1, 1_000_000)
                .onBackpressureBuffer(100000, false, true)
                .map(Counter::new)

//-------- кстати, мы можем менять Scheduler и размер буфера ----------------------------
//                .observeOn(Schedulers.io(), false, 10)
//---------------------------------------------------------------------------------------

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