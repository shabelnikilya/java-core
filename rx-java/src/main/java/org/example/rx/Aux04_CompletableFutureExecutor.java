package org.example.rx;

/*
* Модификация предыдущего примера (Aux03_CompleatableFuture)
* с использованием пула подпроцессов (thread pool).
* */

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Aux04_CompletableFutureExecutor {
    public static void main(String[] args) throws Exception{
        List<Integer> ids = IntStream.range(1, 11)
                .boxed()
                .collect(Collectors.toList());

        LocalTime start = LocalTime.now();

        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<CompletableFuture<Double>> list = ids.stream()
                .map(id -> CompletableFuture.supplyAsync(() ->
                        new Aux02B_MeasuringSystem().get(id), pool))
                .collect(Collectors.toList());
        pool.shutdown();

        LocalTime stop = LocalTime.now();

        double a = list.stream()
                .mapToDouble(cf -> cf.join().doubleValue())
                .average()
                .orElse(0);
        System.out.println((Math.round(a * 100.) / 100.) + " in "
                + Duration.between(start, stop).toMillis() + " ms");  // печатает: 3.87 in 12 ms
    }
}
