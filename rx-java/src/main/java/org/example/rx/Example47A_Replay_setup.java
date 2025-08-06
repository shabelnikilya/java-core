package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example47A_Replay_setup {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> seconds =
                Observable.interval(1, TimeUnit.SECONDS);

        seconds.subscribe(i -> System.out.println("Received 1: " + i));
        Thread.sleep(3000);

        seconds.subscribe(i -> System.out.println("Received 2: " + i));

        Thread.sleep(3000);
    }
}