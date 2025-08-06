package org.example.rx;

/*
* Этот класс собирает данные измерений от 10 датчиков и затем вычисляет среднее 2-мя способами:
* последовательно и параллельно.
* */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Aux02A_ParallelStream {
    public static void main(String[] args) {
        List<Integer> ids = IntStream.range(1, 11)
//                .mapToObj(i -> i)                               // аналог boxed()
                .boxed().toList();
        System.out.print("Sequentially: ");
        Aux02B_MeasuringSystem.getAverage(ids.stream());           // 3.18 in 1124 ms (рандомизировано)
        System.out.print("In parallel : ");
        Aux02B_MeasuringSystem.getAverage(ids.parallelStream());   // 3.22 in 339 ms (то же)
    }
}
