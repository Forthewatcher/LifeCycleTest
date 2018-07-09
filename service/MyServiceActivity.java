package com.example.hzli1.lifecycletest.service;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hzli1.lifecycletest.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MyServiceActivity extends AppCompatActivity {

    @ViewInject(R.id.button26)
    private Button startService;
    @ViewInject(R.id.button27)
    private Button stopService;
    @ViewInject(R.id.textView7)
    private TextView tv;
    MyReceiver rec=new MyReceiver(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        x.view().inject(this);
        //注册接收器
        IntentFilter flt=new IntentFilter();
        flt.addAction("edu.freshen.broadcast");
        registerReceiver(rec,flt);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(rec);//解除注册
    }

    @Event(value=R.id.button26,type = View.OnClickListener.class)
    private void startService(View v){
        Log.i("dddd","启动");
        Intent intent=new Intent(MyServiceActivity.this,MyService.class);
        startService(intent);
    }

    @Event(value=R.id.button27,type = View.OnClickListener.class)
    private void stopService(View v){
        Intent intent=new Intent(MyServiceActivity.this,MyService.class);
        stopService(intent);
    }

    public void setText(String text) {
        tv.setText(text);
    }
}
