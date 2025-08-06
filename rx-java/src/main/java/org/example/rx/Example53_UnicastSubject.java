package org.example.rx;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.UnicastSubject;

public class Example53_UnicastSubject {
    public static void main(String[] args)  {
        UnicastSubject<Integer> source = UnicastSubject.create();

        source.subscribe(v -> System.out.println("Observer 1: "+v));
        source.onNext(1);
        source.onNext(2);

        source.subscribe(v -> System.out.println("Observer 2: "+v), System.out::println);

        source.onNext(3);
        source.onNext(4);
        source.onComplete();

//---------- Еще одна иллюстрация ----------------------------------------------------------------------
//        UnicastSubject<Integer> source = UnicastSubject.create();
//
//        source.onNext(1);
//        source.onNext(2);
//
//        Disposable disposable = source.subscribe(v -> System.out.println("Observer 1: " + v));
//        disposable.dispose();
//        source.onNext(3);
//
//        source.subscribe(v -> System.out.println("Observer 2: "+v),
//                System.out::println);
//
//        source.onNext(4);
//        source.onComplete();


    }
}