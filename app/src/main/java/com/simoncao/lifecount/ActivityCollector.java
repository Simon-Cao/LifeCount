package com.simoncao.lifecount;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by SimonCao on 15/3/15.
 */
public class ActivityCollector {

    public static ArrayList<Activity>activities=new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity:activities){
            activity.finish();
        }
    }
}
