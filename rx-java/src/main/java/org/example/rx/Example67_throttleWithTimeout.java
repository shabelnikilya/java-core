package org.example.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example67_throttleWithTimeout {
    public static void main(String[] args) throws InterruptedException {
        int[] delays = { 200, 1100, 1000,  200, 1400, 700 };
        Observable.range(0, delays.length)
                .map(i -> delays[i])
                .scan(Integer::sum)
                .flatMap(delay -> Observable.just(delay)
                    .delay(delay, TimeUnit.MILLISECONDS)
                    .doOnNext(System.out::println))
                .throttleWithTimeout(1, TimeUnit.SECONDS)
                .subscribe(p -> System.out.println("throttle "+p));
        Thread.sleep(5000);
    }
}


// РЕЗУЛЬТАТ, если закомментировать LOC 15:
// throttle 200
// throttle 1300
// throttle 2500
// throttle 4600

// Объяснение:
//-----------------------------------------------------------
// Дроссель (throttle) эмиттирует постоянно, а устранитель дребезга (debounce) лишь в самом конце интервала.
//
// Пример из жизни:
// Во время скроллинга throttle() будет "приторможенно" вызывать обработчик изображения по мере прокрутки,
// скажем, каждые X миллисекунд, а вот при помощи debounce() мы можем дождаться, пока прокрутка не прекратится
// и лишь затем вызвать обработчик.