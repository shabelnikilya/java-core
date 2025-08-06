package org.example.rx;

import io.reactivex.Observable;
import java.util.stream.IntStream;

public class Aux06_SummedSquareRoots {

    public static void main(String[] args) {

        double a = IntStream.rangeClosed(1, 5)
                .filter(i -> i % 2 == 0)
                .mapToDouble(Math::sqrt)
                .sum();
        System.out.println(a); //prints: 3.414213562373095

        Observable.range(1, 5)
                .filter(i -> i % 2 == 0)
                .map(Math::sqrt)
                .reduce((r, d) -> r + d)
                .subscribe(System.out::println); // печатает: 3.414213562373095

    }
}
