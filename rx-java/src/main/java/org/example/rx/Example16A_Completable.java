package org.example.rx;

import io.reactivex.Completable;

public class Example16A_Completable {
    public static void main(String[] args) {
        Completable.fromRunnable(() -> execute())
                .subscribe(() -> System.out.println("finish"));
    }

    public static void execute() {
        System.out.println("execute");
    }
}