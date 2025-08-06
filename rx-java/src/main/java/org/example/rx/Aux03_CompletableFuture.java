package org.example.rx;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.Duration;
import java.time.LocalTime;

public class Aux03_CompletableFuture {

    public static void main(String[] args) throws Exception{
        List<Integer> ids = IntStream.range(1, 11)
                                     .boxed()
                                     .collect(Collectors.toList());

        LocalTime start = LocalTime.now();

        List<CompletableFuture<Double>> list =
                ids.stream()
                   .map(id -> CompletableFuture.supplyAsync(() ->
                             new Aux02B_MeasuringSystem().get(id)))
                   .collect(Collectors.toList());

        LocalTime stop = LocalTime.now();

        double a = list.stream()
                       .mapToDouble(cf -> cf.join().doubleValue())
                       .average()
                       .orElse(0);
        System.out.println((Math.round(a * 100.) / 100.) + " in "
                + Duration.between(start, stop).toMillis() + " ms");  // печатает: 2.24 in 11 ms
    }
}
