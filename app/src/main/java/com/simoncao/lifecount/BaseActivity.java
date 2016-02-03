package com.simoncao.lifecount;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by SimonCao on 15/3/15.
 */
public class BaseActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
