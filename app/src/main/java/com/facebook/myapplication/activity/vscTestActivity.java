package com.facebook.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.myapplication.R;

public class vscTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vsc_test);
       TextView tv_yy = (TextView) findViewById(R.id.tv_yy);
    }
}
