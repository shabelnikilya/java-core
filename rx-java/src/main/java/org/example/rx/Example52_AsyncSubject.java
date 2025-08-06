package org.example.rx;

import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;

public class Example52_AsyncSubject {
    public static void main(String[] args) {
        AsyncSubject<Integer> source = AsyncSubject.create();

        source.subscribe(v -> System.out.println("Observer 1: "+v));

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        source.subscribe(v -> System.out.println("Observer 2: "+v));

        source.onNext(4);
        source.onComplete();
    }
}