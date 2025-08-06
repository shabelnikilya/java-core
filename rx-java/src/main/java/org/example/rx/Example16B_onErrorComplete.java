package org.example.rx;

import io.reactivex.Completable;
import io.reactivex.Observable;

import java.io.IOException;

public class Example16B_onErrorComplete {
    public static void main(String[] args) {
        Completable.fromAction(() -> {
            throw new IOException();
        }).onErrorComplete(error -> {
            // будем игнорировать исключения лишь с дата-типом java.io.IOException.
            return error instanceof IOException;
        }).subscribe(
            () -> System.out.println("IOException was ignored"),
            error -> System.err.println("onError should not be printed!"));

    }
}