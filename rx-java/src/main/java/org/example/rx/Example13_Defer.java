package org.example.rx;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Example13_Defer {
    private static int start = 1;
    private static int count = 3;

    public static void main(String[] args) {
        Observable<Integer> source = Observable.defer(() ->
                Observable.range(start, count));

        source.subscribe(i -> System.out.println("Observer 1: " + i));

        start = 4;
        count = 5;
        source.subscribe(i -> System.out.println("Observer 2: " + i));
    }
}