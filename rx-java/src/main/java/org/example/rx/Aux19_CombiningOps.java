package org.example.rx;

import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class Aux19_CombiningOps {
    public static void main(String[] args) {
        Observable<String> obs1 = Observable.just("one")
                .flatMap(s -> Observable.fromArray(s.split("")));
        Observable<String> obs2 = Observable.just("two")
                .flatMap(s -> Observable.fromArray(s.split("")));

        Observable.concat(obs2, obs1, obs2)    // <-- обратим внимание на порядок следования!
                .subscribe(System.out::print);                              // печатает: twoonetwo
        System.out.println();

        Observable.combineLatest(obs2, obs1, (x,y) -> "(" + x + y +")")
                .subscribe(System.out::print);                              // печатает (oo)(on)(oe); есть рандомность
        System.out.println();

        obs1.join(obs2, i -> Observable.timer(5, TimeUnit.MILLISECONDS),
                i -> Observable.timer(5, TimeUnit.MILLISECONDS),
                (x,y) -> "("+x+y+")").subscribe(System.out::print);         // печатает: (ot)(nt)(et)(ow)(nw)(ew)(oo)(no)(eo)
        System.out.println();

        Observable.merge(obs2, obs1, obs2)
                .subscribe(System.out::print);                              // печатает twoonetwo; есть рандомность
        System.out.println();

        obs1.startWith("42")
                .subscribe(System.out::print);                              // печатает: 42one
        System.out.println();

        Observable.zip(obs1, obs2, obs1, (x,y,z) -> "("+x+y+z+")")
                .subscribe(System.out::print);                              // печатает: (oto)(nwn)(eoe)
    }
}
