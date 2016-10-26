package com.simoncao.lifecount;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends BaseActivity {

     /*
     dt是日期的保存的格式，可以通过这个格式方便的进行Date和String数据类型的转换
     deathString是死亡日的String类数据
     deathday是死亡日的Date类数据
     count是居中显示的倒计时表
     */
    DateFormat dt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String deathString;
    Date deathday;
    TextView count;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=(TextView)findViewById(R.id.counter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //因为和onCreat在不同方法中，所以onStart中必须再次实例化SharedPreferences
        SharedPreferences share=getSharedPreferences("Data",MODE_PRIVATE);
//        SharedPreferences.Editor editor=share.edit();
//        editor.clear();
//        editor.apply();
        deathString=share.getString("Time","default");
        Log.d("Tag",deathString);

        /*
        判断从SharedPreferences中取出的值，如果是default则表明是用户第一次打开应用，或者用户重新建立倒计时
        则进入页面二，通过一系列问题来确定死亡时间deathday
        */
        if(deathString.equals("default")){
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            finish();
            startActivity(intent);
        }else{
            try{
                deathday=dt.parse(deathString); //deathday在这里被转成时间格式
            }catch (Exception e){
                e.printStackTrace();
            }


            //通过Calendar来获取当前的系统时间current
            Calendar ca=Calendar.getInstance();
            Date current=ca.getTime();
            Long lifetime=deathday.getTime()-current.getTime();

            //利用CountDownTimer来进行倒计时，分别按日，时，分，秒显示出来

             CountDownTimer cdt=new CountDownTimer(lifetime,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Long day=millisUntilFinished/1000/60/60/24;
                    Long hour=(millisUntilFinished-day*1000*60*60*24)/1000/60/60;
                    Long minute=(millisUntilFinished-day*1000*60*60*24-hour*1000*60*60)/1000/60;
                    Long second=(millisUntilFinished-day*1000*60*60*24-hour*1000*60*60-minute*1000*60)/1000;

                /*小于10秒的时候，秒数会变成一位数，这个时候整条TextView会缩短一截，然后到了两位数的秒数的时候，又会突然增长一截
                  这样非常影响客户体验，所以添加了下面的判断，当秒数小于10的时候，在前面多打一个0，完美解决
                  小时数和分钟数同理解决，天数不做变动
                */

                    String Thour=hour.toString();
                    String Tminute=minute.toString();
                    String Tsecond=second.toString();

                    if(hour<10){
                        Thour="0"+Thour;
                    }
                    if(minute<10){
                        Tminute="0"+minute;
                    }
                    if(second<10){
                        Tsecond="0"+second;
                    }

                    count.setText(day+"天 "+Thour+"时 "+Tminute+"分 "+Tsecond+"秒");
                }


                @Override
                public void onFinish() {
                    count.setText(" See you in Heaven ");

                }
            };
//            CounterCollector.addCounter(cdt);
            cdt.start();

        }
    }

    @Override
    protected void onPause() {
        CounterCollector.finishAll();
        super.onPause();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*
        用户点击new count后，页面会转到确认页面
        一方面可以防止用户的误操作，另一方面因为我们的倒计时是放在MainActivity的onStart()方法中的
        从别的Activity跳转回MainActivity时，会重新运算onStart()，重新读取文档中的deathday来进行新的倒计时
        */
        switch (id){
            case R.id.action_settings:{
                Intent intent=new Intent(MainActivity.this,Confirm.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_about:{
                Intent intent=new Intent(MainActivity.this,About.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    //为了优化客户体验，这里设置主页面按返回键，全部退出
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCollector.finishAll();
    }
}
