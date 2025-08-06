package org.example.rx;

import io.reactivex.Observable;

public class Example34B_Reduce {
    public static void main(String[] args) {
        Observable.range(1, 10)
                .reduce((total, next) -> total + next)
                .subscribe(total -> System.out.println("Total: " + total));

    }

}