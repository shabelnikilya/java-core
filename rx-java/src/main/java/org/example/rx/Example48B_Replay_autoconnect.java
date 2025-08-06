package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example48B_Replay_autoconnect {
    public static void main(String[] args) throws InterruptedException {
        // хотя replay().autoConnect() аналогичен поведению оператора cache(),
        // replay() можно настраивать (напр., время кеширования) и он обладает большей гибкостью
        Observable<Long> seconds =
                Observable.interval(1, TimeUnit.SECONDS)
                        .replay(2, TimeUnit.SECONDS)
                        .autoConnect();

        seconds.subscribe(i -> System.out.println("Received 1: " + i));
        Thread.sleep(3000);

        seconds.subscribe(i -> System.out.println("Received 2: " + i));

        Thread.sleep(3000);
    }
}