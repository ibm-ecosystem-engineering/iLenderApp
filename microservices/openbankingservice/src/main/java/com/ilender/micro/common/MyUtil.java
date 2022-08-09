package com.ilender.micro.common;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyUtil {


    public static int getRandom(int min, int max){
        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    public static void sleepForSomeTime() {
        int seconds = getRandom(1,20);


        try {
            // delay seconds
            TimeUnit.SECONDS.sleep(seconds);

            // delay 0.5 second
            //TimeUnit.MICROSECONDS.sleep(500);

            // delay 1 minute
            //TimeUnit.MINUTES.sleep(1);

        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

}
