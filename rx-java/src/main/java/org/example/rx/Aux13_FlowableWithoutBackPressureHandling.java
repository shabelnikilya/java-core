package org.example.rx;

/*
 * Бросает MissingBackpressureException при заполнении буфера до отказа.
 * */

import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

public class Aux13_FlowableWithoutBackPressureHandling {

    public static void main(String[] args) {
        PublishProcessor<Integer> hot = PublishProcessor.create();
        hot.observeOn(Schedulers.io(), true)
//***********************************************************************************************************
           // внедрение задержки только усугубит ситуацию: даже первые 128 элементов не отработают!
//           .delay(10, TimeUnit.MILLISECONDS)
//***********************************************************************************************************
           .subscribe(System.out::println, Throwable::printStackTrace);
        for (int i = 0; i < 1_000_000; i++) {
            hot.onNext(i);
        }

    }
}
