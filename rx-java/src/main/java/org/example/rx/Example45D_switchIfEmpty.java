package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.concurrent.TimeUnit;

public class Example45D_switchIfEmpty {
    public static Observable<String> fromCache() {
        return Observable.<String>empty();
    }
    public static Observable<String> errorIfEmpty() {
        return Observable.error(new Exception("no data!"));
    }
    public static Observable<String> fromNetwork() {
        return Observable.just("this", "is", "from", "network");
    }

    public static void main(String[] args) throws InterruptedException {
        // Сценарий 1. Если кеш пуст, берем данные из сети
        Observable.defer(() -> fromCache())
        .switchIfEmpty(fromNetwork())
        .subscribe(System.out::println);

        System.out.println("-------------------------------------------------------------------------");

        // Сценарий 2. Если кеш пуст, бросаем ошибку
        Observable.defer(() -> fromCache())
                .switchIfEmpty(errorIfEmpty())
                .subscribe(System.out::println,
                        err -> System.out.println(
                                "ERROR: " + err));
        Thread.sleep(1000);
    }
}