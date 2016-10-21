package com.simoncao.lifecount;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LiftCount_Service extends Service {

    DateFormat dt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String deathString;
    Date deathday;
    TextView count;
    CountDownTimer cdt;

    public LiftCount_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public void onCreate(){
        super.onCreate();
        //从SharedPreference中获取死亡日期的String值: deathString
        SharedPreferences share=getSharedPreferences("Data",MODE_PRIVATE);
        deathString=share.getString("Time","default");
        Log.d("Tag",deathString);

        //deathday在这里被转成时间格式
        if(deathString.equals("default")){
            RemoteViews rv=new RemoteViews(getPackageName(),R.layout.life__count);
            rv.setTextViewText(R.id.appwidget_text,"请先在App中设置一个Life Counter");
        }else {
            try {
                deathday = dt.parse(deathString); //deathday在这里被转成时间格式
            } catch (Exception e) {
                e.printStackTrace();
            }

            //通过Calendar来获取当前的系统时间current
            Calendar ca = Calendar.getInstance();
            Date current = ca.getTime();
            Long lifetime = deathday.getTime() - current.getTime();

            //设置倒计时
            cdt = new CountDownTimer(lifetime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Long day = millisUntilFinished / 1000 / 60 / 60 / 24;
                    Long hour = (millisUntilFinished - day * 1000 * 60 * 60 * 24) / 1000 / 60 / 60;
                    Long minute = (millisUntilFinished - day * 1000 * 60 * 60 * 24 - hour * 1000 * 60 * 60) / 1000 / 60;
                    Long second = (millisUntilFinished - day * 1000 * 60 * 60 * 24 - hour * 1000 * 60 * 60 - minute * 1000 * 60) / 1000;

                /*小于10秒的时候，秒数会变成一位数，这个时候整条TextView会缩短一截，然后到了两位数的秒数的时候，又会突然增长一截
                  这样非常影响客户体验，所以添加了下面的判断，当秒数小于10的时候，在前面多打一个0，完美解决
                  小时数和分钟数同理解决，天数不做变动
                */

                    String Thour = hour.toString();
                    String Tminute = minute.toString();
                    String Tsecond = second.toString();

                    if (hour < 10) {
                        Thour = "0" + Thour;
                    }
                    if (minute < 10) {
                        Tminute = "0" + minute;
                    }
                    if (second < 10) {
                        Tsecond = "0" + second;
                    }

                    String count = day + "天 " + Thour + "时 " + Tminute + "分 " + Tsecond + "秒";
//                count.setText(day+"天 "+Thour+"时 "+Tminute+"分 "+Tsecond+"秒");

                    RemoteViews rv = new RemoteViews(getPackageName(), R.layout.life__count);
                    rv.setTextViewText(R.id.appwidget_text, count);
                    AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
                    ComponentName cn = new ComponentName(getApplicationContext(), Life_Count.class);
                    manager.updateAppWidget(cn, rv);
                }

                public void onFinish() {
                    count.setText(" See you in Heaven ");
                }
            };
            CounterCollector.addCounter(cdt);
            cdt.start();
        }
    }


    public void onDestroy(){
        super.onDestroy();
        cdt=null;
    }

}
