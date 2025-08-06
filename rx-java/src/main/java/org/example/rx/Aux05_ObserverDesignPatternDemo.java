package org.example.rx;

/*
* Прямолинейная имплементация шаблона проектирования Observer:
* некий Fan подписывается на твиты RockStar'а.
* --------------------------------------------------------------
* Заполняется непосредственно во время лекции (live coding).
* */

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Aux05_ObserverDesignPatternDemo {
    static interface Tweetable{

        void tweet();
    }
    static class Fan implements Tweetable {

        @Override
        public void tweet() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fan: I was tweeted on: " + Thread.currentThread().getName());
        }
    }

    static class AnotherFan implements Tweetable {

        @Override
        public void tweet() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Second fan: I was tweeted on: " + Thread.currentThread().getName());
        }
    }
    static class SyncRockStar {

        private final List<Tweetable> fans = new ArrayList<>();


        public void add(Tweetable tweetable) {
            fans.add(tweetable);
        }

        public void sendTweet() {
            fans.forEach(Tweetable::tweet);
        }
    }

    static class AsyncRockStar {

        private final List<Tweetable> fans = new ArrayList<>();


        public void add(Tweetable tweetable) {
            fans.add(tweetable);
        }

        public void sendTweet() {
            var tasks = fans.stream()
                    .map(f -> CompletableFuture.runAsync(f::tweet, Executors.newCachedThreadPool()))
                    .toList();

            tasks.forEach(CompletableFuture::join);
        }
    }

    public static void main(String[] args) {
        SyncRockStar rockStar = new SyncRockStar();
        rockStar.add(new Fan());
        rockStar.add(new AnotherFan());

        rockStar.sendTweet(); // синхронная отправка твитов
        System.out.println("запуск синхронный рассылки твитов");
        System.out.println("________________________________");

        var start = System.currentTimeMillis();
        CompletableFuture<Void> asyncTweet = CompletableFuture.runAsync(rockStar::sendTweet); // асинхронная
        asyncTweet.thenRun(() -> System.out.println("Асинхронное выполнение задачи окончено"));
        System.out.println("между результатом асинхронной задачи");
        asyncTweet.join();
        var end = System.currentTimeMillis() - start;
        System.out.println("первый асинхронный вариант: " + end);

        System.out.println("________________");
        AsyncRockStar asyncRockStar = new AsyncRockStar();
        asyncRockStar.add(new Fan());
        asyncRockStar.add(new AnotherFan());

        var asyncStart = System.currentTimeMillis();
        asyncRockStar.sendTweet();
        var asyncEnd = System.currentTimeMillis() - asyncStart;
        System.out.println("Второй асинхронный вариант: " + asyncEnd);
    }
}



