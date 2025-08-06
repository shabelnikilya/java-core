package org.example.rx;

/*
* Демонстрируем работу с map(), flatMap() и groupBy()
* */
import io.reactivex.Observable;
import java.util.*;

public class Aux17A_TransformingOps {

    public static void main(String[] args) {

        Observable<String> obs = Observable.fromArray("one", "two");

        obs.map(s -> s.contains("w") ? 1 : 0)
           .forEach(System.out::print);                     // печатает: 01

        System.out.println();

        List<String> os = new ArrayList<>();                // 'os' собирает обзерваблы, содержащие 'o'
        List<String> noto = new ArrayList<>();              // 'noto' собирает обзерваблы, НЕ содержащие 'o'

        obs.flatMap(s -> Observable.fromArray(s.split("")))     // obs выщелкивает "onetwo" по-символьно
           .groupBy(s -> "o".equals(s) ? "o" : "noto")      // эмиттирует объекты Observable<GroupedObservable<K,T>>
           .subscribe(g -> g.subscribe(s -> {
               if (g.getKey().equals("o")) {
                   os.add(s);
               } else {
                   noto.add(s);
               }
           }));

        System.out.println(os);                             // печатает: [o, o]
        System.out.println(noto);                           // печатает: [n, e, t, w]
    }
}
