package org.example.rx;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Example77_TestScheduler {

    @Test
    public void testObservable() {
        TestScheduler testScheduler = new TestScheduler();
        TestObserver<Long> testObserver = new TestObserver<>();
        Observable<Long> minuteTicker =
                Observable.interval(1, TimeUnit.MINUTES,
                        testScheduler);
        minuteTicker.subscribe(testObserver);

//----- Что и как будем тестировать: --------------------------------------------------------
//        Делаем "прокрутку вперед" на 30 секунд после подписки
//        Удостоверяемся, что эмиссий не было
//        Делаем "прокрутку вперед" на 70 секунд после подписки
//        Удостоверяемся, что имела место лишь одна эмиссия
//        Делаем "прокрутку вперед" на 90 минут после подписки
//        Удостоверяемся, что имели место 90 эмиссий

        testScheduler.advanceTimeBy(30, TimeUnit.SECONDS);
        testObserver.assertValueCount(0);
        testScheduler.advanceTimeTo(70, TimeUnit.SECONDS);
        testObserver.assertValueCount(1);
        testScheduler.advanceTimeTo(90, TimeUnit.MINUTES);
        testObserver.assertValueCount(90);

    }

}