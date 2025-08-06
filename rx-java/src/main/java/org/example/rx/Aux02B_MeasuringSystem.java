package org.example.rx;

/*
 * Имитатор системы, которая опрашивает физические устройства (датчики) и вычисляет среднее.
 */

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Aux02B_MeasuringSystem {

    static double get(int id) {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        return id * Math.random();  // <--- вводит рандомизацию
    }

    static void getAverage(Stream<Integer> ids) {
        LocalTime start = LocalTime.now();
        double a = ids.mapToDouble(id -> new Aux02B_MeasuringSystem().get(id))
                      .average()
                      .orElse(0);
        System.out.println((Math.round(a * 100.) / 100.) + " in " +
                Duration.between(start, LocalTime.now()).toMillis() + " ms");
    }
}
