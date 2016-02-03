package com.simoncao.lifecount;

import android.app.Activity;
import android.os.CountDownTimer;

import java.util.ArrayList;

/**
 * Created by SimonCao on 15/3/21.
 */
public class CounterCollector {

    public static ArrayList<CountDownTimer> counters=new ArrayList<CountDownTimer>();

    public static void addCounter(CountDownTimer counter){
        counters.add(counter);
    }

    public static void removeCounter(CountDownTimer counter){
        counters.remove(counter);
    }

    public static void finishAll(){
        for(CountDownTimer counter:counters){
            counter.cancel();
        }
    }
}
