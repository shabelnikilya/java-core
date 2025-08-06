package org.example.rx;

import java.util.concurrent.TimeUnit;

public class Aux01_CommonUtils {

    public static void pauseMs(long ms){
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
    }

}
