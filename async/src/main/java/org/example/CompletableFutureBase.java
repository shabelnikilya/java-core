package org.example;

import java.util.Random;

/*
 * Общие методы для всех упражнений
*/

public class CompletableFutureBase {

    public Integer slowInit() {
        System.out.println("started task slowInit()"
           + " by " + Thread.currentThread().getName()  // раскомментировать для Exercise CF03_Several_Pipelines
        );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return 1;
    }

    public Integer slowIncrement(Integer i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("finished incrementing with the result " + (i + 1));
        System.out.println(Thread.currentThread().getName());
        return (i + 1);
    }

    public Integer slowIncrementException(Integer i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        Random rand = new Random();
        if (rand.nextBoolean())
            throw new MyException(i);
        return (i + 1);
    }
    
}
