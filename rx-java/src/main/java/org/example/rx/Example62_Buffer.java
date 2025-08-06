package org.example.rx;

import io.reactivex.Observable;

public class Example62_Buffer {
    public static void main(String[] args) {
        Observable.range(1,100)
                .buffer(16)
                .subscribe(System.out::println);
    }
}