package org.example.rx;

import io.reactivex.Observable;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Example33B_Timestamp {
    public static void main(String[] args) throws InterruptedException{
        Observable.interval(1, TimeUnit.SECONDS)
            .take(3)
            .timestamp()

//--------- blockingSubscribe() нужен, т.к. main()-thread вернется еще до отработки вызова interval()
            .blockingSubscribe(val -> {
                System.out.println(Instant.ofEpochMilli(val.time()));
                System.out.println(val.value());
            });
//---------------------------------------------------------------------------------------------------


//--------- можем обойтись и без блокирующей подписки, но придется поспать --------------------------
//            .subscribe(val -> {
//                    System.out.println(Instant.ofEpochMilli(val.time()));
//                    System.out.println(val.value());
//            });
//        Thread.sleep(4000);
//---------------------------------------------------------------------------------------------------

    }
}