package com.simoncao.lifecount;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerTitleStrip;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;


public class Question extends BaseActivity {

    private int life;
    private int year;
    private int month;
    private int day;
    DateFormat dt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String deathString;
    Button button;
    RadioButton rb1_1,rb1_2,rb2_1,rb2_2,rb3_1,rb3_2,rb3_3,rb4_1,rb4_2,rb5_1,rb5_2,rb6_1,rb6_2,rb6_3,
                rb7_1,rb7_2,rb8_1,rb8_2,rb8_3,rb9_1,rb9_2,rb10_1,rb10_2,rb10_3,rb10_4,rb10_5,rb11_1,rb11_2,rb11_3,
                rb12_1,rb12_2,rb13_1,rb13_2,rb14_1,rb14_2,rb15_1,rb15_2,rb16_1,rb16_2,rb16_3,rb16_4,
                rb17_1,rb17_2,rb17_3,rb18_1,rb18_2,rb19_1,rb19_2,rb20_1,rb20_2,rb21_1,rb21_2,rb22_1,rb22_2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        life=84;//健康男性的平均寿命为84岁

        rb1_1=(RadioButton)findViewById(R.id.rb1_1);
        rb1_2=(RadioButton)findViewById(R.id.rb1_2);
        rb2_1=(RadioButton)findViewById(R.id.rb2_1);
        rb2_2=(RadioButton)findViewById(R.id.rb2_2);
        rb3_1=(RadioButton)findViewById(R.id.rb3_1);
        rb3_2=(RadioButton)findViewById(R.id.rb3_2);
        rb3_3=(RadioButton)findViewById(R.id.rb3_3);
        rb4_1=(RadioButton)findViewById(R.id.rb4_1);
        rb4_2=(RadioButton)findViewById(R.id.rb4_2);
        rb5_1=(RadioButton)findViewById(R.id.rb5_1);
        rb5_2=(RadioButton)findViewById(R.id.rb5_2);
        rb6_1=(RadioButton)findViewById(R.id.rb6_1);
        rb6_2=(RadioButton)findViewById(R.id.rb6_2);
        rb6_3=(RadioButton)findViewById(R.id.rb6_3);
        rb7_1=(RadioButton)findViewById(R.id.rb7_1);
        rb7_2=(RadioButton)findViewById(R.id.rb7_2);
        rb8_1=(RadioButton)findViewById(R.id.rb8_1);
        rb8_2=(RadioButton)findViewById(R.id.rb8_2);
        rb8_3=(RadioButton)findViewById(R.id.rb8_3);
        rb9_1=(RadioButton)findViewById(R.id.rb9_1);
        rb9_2=(RadioButton)findViewById(R.id.rb9_2);
        rb10_1=(RadioButton)findViewById(R.id.rb10_1);
        rb10_2=(RadioButton)findViewById(R.id.rb10_2);
        rb10_3=(RadioButton)findViewById(R.id.rb10_3);
        rb10_4=(RadioButton)findViewById(R.id.rb10_4);
        rb10_5=(RadioButton)findViewById(R.id.rb10_5);
        rb11_1=(RadioButton)findViewById(R.id.rb11_1);
        rb11_2=(RadioButton)findViewById(R.id.rb11_2);
        rb11_3=(RadioButton)findViewById(R.id.rb11_3);
        rb12_1=(RadioButton)findViewById(R.id.rb12_1);
        rb12_2=(RadioButton)findViewById(R.id.rb12_2);
        rb13_1=(RadioButton)findViewById(R.id.rb13_1);
        rb13_2=(RadioButton)findViewById(R.id.rb13_2);
        rb14_1=(RadioButton)findViewById(R.id.rb14_1);
        rb14_2=(RadioButton)findViewById(R.id.rb14_2);
        rb15_1=(RadioButton)findViewById(R.id.rb15_1);
        rb15_2=(RadioButton)findViewById(R.id.rb15_2);
        rb16_1=(RadioButton)findViewById(R.id.rb16_1);
        rb16_2=(RadioButton)findViewById(R.id.rb16_2);
        rb16_3=(RadioButton)findViewById(R.id.rb16_3);
        rb16_4=(RadioButton)findViewById(R.id.rb16_4);
        rb17_1=(RadioButton)findViewById(R.id.rb17_1);
        rb17_2=(RadioButton)findViewById(R.id.rb17_2);
        rb17_3=(RadioButton)findViewById(R.id.rb17_3);
        rb18_1=(RadioButton)findViewById(R.id.rb18_1);
        rb18_2=(RadioButton)findViewById(R.id.rb18_2);
        rb19_1=(RadioButton)findViewById(R.id.rb19_1);
        rb19_2=(RadioButton)findViewById(R.id.rb19_2);
        rb20_1=(RadioButton)findViewById(R.id.rb20_1);
        rb20_2=(RadioButton)findViewById(R.id.rb20_2);
        rb21_1=(RadioButton)findViewById(R.id.rb21_1);
        rb21_2=(RadioButton)findViewById(R.id.rb21_2);
        rb22_1=(RadioButton)findViewById(R.id.rb22_1);
        rb22_2=(RadioButton)findViewById(R.id.rb22_2);












        //将输入生日的代码放在最下面的Button触发方法里
        button=(Button)findViewById(R.id.setBirthday);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rb1_2.isChecked()){
                    life+=5;
                }
                if(rb2_1.isChecked()){
                    life+=0.75;
                }
                if(rb2_2.isChecked()){
                    life-=0.5;
                }
                if(rb3_1.isChecked()){
                    life-=2;
                }
                if(rb3_2.isChecked()){
                    life-=1;
                }
                if(rb3_3.isChecked()){
                    life+=0.5;
                }
                if(rb4_1.isChecked()){
                    life+=0.75;
                }
                if(rb4_2.isChecked()){
                    life-=0.25;
                }
                if(rb5_1.isChecked()){
                    life-=1;
                }
                if(rb5_2.isChecked()){
                    life+=0.25;
                }
                if(rb6_1.isChecked()){
                    life+=0.25;
                }
                if(rb6_3.isChecked()){
                    life-=0.5;
                }
                if(rb7_1.isChecked()){
                    life+=0.25;
                }
                if(rb7_2.isChecked()){
                    life-=0.5;
                }
                if(rb8_1.isChecked()){
                    life+=0.75;
                }
                if(rb8_3.isChecked()){
                    life-=1.25;
                }
                if(rb9_1.isChecked()){
                    life-=0.5;
                }
                if(rb10_2.isChecked()){
                    life-=0.5;
                }
                if(rb10_3.isChecked()){
                    life-=1;
                }
                if(rb10_4.isChecked()){
                    life-=2;
                }
                if(rb10_5.isChecked()){
                    life-=0.5;
                }
                if(rb11_3.isChecked()){
                    life-=1;
                }
                if(rb12_1.isChecked()){
                    life-=3;
                }
                if(rb13_2.isChecked()){
                    life-=1;
                }
                if(rb14_2.isChecked()){
                    life-=1;
                }
                if(rb15_1.isChecked()){
                    life-=1;
                }
                if(rb16_1.isChecked()){
                    life-=0.25;
                }
                if(rb16_2.isChecked()){
                    life+=0.25;
                }
                if(rb16_3.isChecked()){
                    life-=0.5;
                }
                if(rb16_4.isChecked()){
                    life-=1.25;
                }
                if(rb17_2.isChecked()){
                    life+=0.5;
                }
                if(rb17_3.isChecked()){
                    life+=0.75;
                }
                if(rb18_1.isChecked()){
                    life+=0.25;
                }
                if(rb18_2.isChecked()){
                    life-=0.5;
                }
                if(rb19_1.isChecked()){
                    life-=3;
                }
                if(rb20_1.isChecked()){
                    life-=2;
                }
                if(rb21_1.isChecked()){
                    life-=2;
                }
                if(rb22_1.isChecked()){
                    life+=2;
                }




                /**
                 * 构造函数原型：
                 * public DatePickerDialog (Context context, DatePickerDialog.OnDateSetListener callBack,
                 * int year, int monthOfYear, int dayOfMonth)
                 * content组件运行Activity，
                 * DatePickerDialog.OnDateSetListener：选择日期事件
                 * year：当前组件上显示的年，monthOfYear：当前组件上显示的月，dayOfMonth：当前组件上显示的第几天
                 *
                 */
                //创建DatePickerDialog对象,初始化默认日期为1980-1-1
                DatePickerDialog dpd=new DatePickerDialog(Question.this,Datelistener,1980,0,1);
                DatePicker dp=dpd.getDatePicker();
                Calendar minDate=new GregorianCalendar(1950,0,1);
                Calendar maxDate=new GregorianCalendar(2015,0,1);
                dp.setMinDate(minDate.getTime().getTime());
                dp.setMaxDate(maxDate.getTime().getTime());
                dpd.show();//显示DatePickerDialog组件
            }
        });


    }

    private DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener()
    {
        /**params：view：该事件关联的组件
         * params：myyear：当前选择的年
         * params：monthOfYear：当前选择的月
         * params：dayOfMonth：当前选择的日
         */
        @Override
        public void onDateSet(DatePicker view, int myyear, int monthOfYear,int dayOfMonth) {
            //用三个变量year，month，day来接受用户选择的生日
            year=myyear;
            month=monthOfYear;
            day=dayOfMonth;

           // 更新日期
            updateDate();
        }
        //当DatePickerDialog关闭时，更新日期显示
        private void updateDate()
        {
            //获得String格式的死亡数据
            deathString=(year+life)+"-"+(month+1)+"-"+day+" 00:00:00";
            Log.d("Tag","计算后的死亡日期为"+deathString);
            SharedPreferences share=getSharedPreferences("Data",MODE_PRIVATE);
            SharedPreferences.Editor editor=share.edit();
            editor.putString("Time",deathString);
            editor.apply();

            AlertDialog.Builder dialog=new AlertDialog.Builder(Question.this);
            dialog.setTitle("预计你的生命时间为：");
            dialog.setMessage(life+"年");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(Question.this,MainActivity.class);
                    startActivity(intent);
                }
            });
            dialog.show();
        }
    };









}
