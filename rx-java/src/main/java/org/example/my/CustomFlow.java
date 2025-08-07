package org.example.my;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class CustomFlow {

    static interface Publisher<T> {

        void subscribe(Subscriber<T> subscriber);
    }

    static class CustomPublisher implements Publisher<String> {

        private final List<Subscriber<String>> subscribers = new ArrayList<>();
        private final List<String> data;

        public CustomPublisher(List<String> data) {
            this.data = data;
        }

        @Override
        public void subscribe(Subscriber<String> subscriber) {
            subscribers.add(subscriber);

            subscriber.onSubscribe(new SimpleSubscription(subscriber, data));
        }
    }

    static interface Subscriber<T> {

        void onSubscribe(Subscription subscription);

        void onNext(T t);

        void onCancel();
    }

   static class StringSubscriber implements Subscriber<String> {

        @Override
        public void onSubscribe(Subscription subscription) {
            System.out.println("подписка осуществленна");
            subscription.request(2);
        }

        @Override
        public void onNext(String s) {
            System.out.println("получен элемент: " + s);
        }

        @Override
        public void onCancel() {
            System.out.println("Терминальная операция завершения");
        }
    }

    static interface Subscription {

        void request(int amount);

        void cancel();
    }

    static class SimpleSubscription implements Subscription {

        private final Subscriber<String> subscriber;
        private final List<String> data;

        private int currentAmount = 1;
        private boolean isCancel = false;

        public SimpleSubscription(Subscriber<String> subscriber, List<String> data) {
            this.subscriber = subscriber;
            this.data = data;
        }

        @Override
        public void request(int amount) {
            this.currentAmount = amount;

            if (!isCancel) {
                for (int i = 0; i < currentAmount && i < data.size(); i++) {
                    subscriber.onNext(data.get(i));
                }
            }
        }

        @Override
        public void cancel() {
            this.isCancel = true;
        }

        public int getCurrentAmount() {
            return currentAmount;
        }

        public boolean isCancel() {
            return isCancel;
        }
    }

    public static void main(String[] args) {
        var pub = new CustomPublisher(List.of("1", "2", "3"));
        var subscriber = new StringSubscriber();

        pub.subscribe(subscriber);
    }
}
