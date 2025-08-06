package org.example.rx;

/*
* Прямолинейная имплементация шаблона проектирования Observer:
* некий Fan подписывается на твиты RockStar'а.
* --------------------------------------------------------------
* Заполняется непосредственно во время лекции (live coding).
* */

import java.util.*;
import java.util.concurrent.CompletableFuture;

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
            System.out.println("Second fan: I was tweeted on: " + Thread.currentThread().getName());
        }
    }
    static class RockStar{

        private final List<Tweetable> fans = new ArrayList<>();


        public void add(Tweetable tweetable) {
            fans.add(tweetable);
        }

        public void sendTweet() {
            fans.forEach(Tweetable::tweet);
        }
    }

    public static void main(String[] args) {
        RockStar rockStar = new RockStar();
        rockStar.add(new Fan());
        rockStar.add(new AnotherFan());

        rockStar.sendTweet(); // синхронная отправка твитов
        System.out.println("запуск синхронный рассылки твитов");
        System.out.println("________________________________");

        CompletableFuture<Void> asyncTweet = CompletableFuture.runAsync(rockStar::sendTweet); // асинхронная
        asyncTweet.thenRun(() -> System.out.println("Асинхронное выполнение задачи окончено"));
        System.out.println("между результатом асинхронной задачи");
        asyncTweet.join();
    }
}



