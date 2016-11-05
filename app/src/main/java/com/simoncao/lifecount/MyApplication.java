package com.simoncao.lifecount;

import android.app.Application;
import android.content.Context;

/**
 * Created by SimonCao on 11/5/16.
 */

public class MyApplication extends Application {
    private static Context context;

    public void onCreate(){
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
