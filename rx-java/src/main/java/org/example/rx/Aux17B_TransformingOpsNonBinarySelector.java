package org.example.rx;

/*
* Использует небинарный селекторный ключ в форме самостоятельной лямбда-функции
* */

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

public class Aux17B_TransformingOpsNonBinarySelector {

    public static void main(String[] args) {

        Observable<String> obs = Observable.fromArray("one", "two", "three");

        List<String> os = new ArrayList<>();
        List<String> es = new ArrayList<>();
        List<String> notoe = new ArrayList<>();

        Function<String, String> keySelector = s -> {
            String group = null;
            switch (s) {
                case "o":
                    group = "o";
                    break;
                case "e":
                    group = "e";
                    break;
                default:
                    group = "notoe";
                    break;
            }
            return group;
        };

        obs.flatMap(s -> Observable.fromArray(s.split("")))
                .groupBy(keySelector)
                .subscribe(g -> g.subscribe(s -> {
                    if (g.getKey().equals("o")) {
                        os.add(s);
                    } else if (g.getKey().equals("e")) {
                        es.add(s);
                    } else {
                        notoe.add(s);
                    }
                }));

        System.out.println(os);                             // печатает: [o, o]
        System.out.println(es);                             // печатает: [e, e, e]
        System.out.println(notoe);                          // печатает: [n, t, w, t, h, r]
    }
}
