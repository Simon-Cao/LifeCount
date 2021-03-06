package com.simoncao.lifecount;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
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
    SharedPreferences share;
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
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder mbuild=new NotificationCompat.Builder(this);
        mbuild.setSmallIcon(R.mipmap.sandglass)
                .setContentTitle("Count Down Your Life")
                .setContentText("Time is limited source, Make it Valuable")
                .setContentIntent(pendingIntent);
        Notification notification=mbuild.build();
        startForeground(1,notification);
    }

    public int onStartCommand(Intent intent,int flags,int startId){
        updateView();
        return super.onStartCommand(intent,flags,startId);
    }

    public void updateView(){

        //从SharedPreference中获取死亡日期的String值: deathString
        share=getSharedPreferences("Data",MODE_PRIVATE);
        deathString=share.getString("Time","default");


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

                    String count = day + "\n" + Thour + ":" + Tminute + ":" + Tsecond;


                    RemoteViews rv = new RemoteViews(getPackageName(), R.layout.life__count);
                    rv.setTextViewText(R.id.appwidget_text, count);
                    AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
                    ComponentName cn = new ComponentName(getApplicationContext(), Life_Count.class);
                    manager.updateAppWidget(cn, rv);

                    Log.d("Tag","CountDownTimer Thread "+Thread.currentThread().getId());
                }

                public void onFinish() {
                    count.setText(" See you in Heaven ");
                }
            };
            cdt.start();
        }
    }


    public void onDestroy(){
        super.onDestroy();
        cdt.cancel();
    }

}
