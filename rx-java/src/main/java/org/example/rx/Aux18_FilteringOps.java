package org.example.rx;

import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class Aux18_FilteringOps {

    public static void main(String[] args) {
        Observable<String> obs = Observable.just("onetwo")
                .flatMap(s -> Observable.fromArray(s.split("")));

        obs.map(s -> {
            if("t".equals(s)){
                Aux01_CommonUtils.pauseMs(15);
            }
            return s;
            })
           .debounce(10, TimeUnit.MILLISECONDS)
// Логика работы debounce(): этот оператор эмиттирует лишь если не появился очередной элемент в пределах
// заданного интервала
// Итак, символы 'o', 'n' и 'e' влетают без задержек, поэтому debounce() их не пропускает; затем конвейер
// приостановился на 't', вот почему после 10 мсек debounce() выдал последний элемент, который в него
// влетел, а именно, символ 'e'.
// Затем еще через 5 мсек символ 't' наконец появился в конвейере и debounce() его придержал, потом
// он придержал 'w' и 'o', и наконец еще через 10 мсек debounce() выпустил наружу последний
// элемент, который в нем хранился, т.е. символ 'o', и на этом конвейер истощился.
           .forEach(System.out::print);                            // печатает: eo

        obs.distinct().forEach(System.out::print);                 // печатает: onetw

        obs.elementAt(3).subscribe(System.out::println);      // печатает: t

        obs.filter(s -> s.equals("o"))
           .forEach(System.out::print);                            // печатает: oo

        obs.firstElement().subscribe(System.out::println);         // печатает: o

        obs.ignoreElements().subscribe(() ->
                System.out.println("Completed!"));                 // печатает: Completed!

        Observable.interval(5, TimeUnit.MILLISECONDS)
                .sample(10, TimeUnit.MILLISECONDS)           // если закомментировать, даст 15 значений, т.к. 5х3=15
                .subscribe(v -> System.out.print(v + " "));        // печатает растущий ряд, напр., 0 2 4 6 8 10 12


    //--- Итак, почему нам нужен этот LOC?
        Aux01_CommonUtils.pauseMs(75);


    }
}
