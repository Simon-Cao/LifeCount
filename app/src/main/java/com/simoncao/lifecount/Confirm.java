package com.simoncao.lifecount;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Confirm extends BaseActivity {

    Button yes,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        yes=(Button)findViewById(R.id.yes);
        no=(Button)findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取SharedPreferences实例，并清除内容
                SharedPreferences share=getSharedPreferences("Data",MODE_PRIVATE);
                SharedPreferences.Editor editor=share.edit();
                editor.clear();
                editor.apply();


                //返回首页
                Intent intent=new Intent(Confirm.this,MainActivity.class);
                startActivity(intent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
