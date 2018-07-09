package com.example.hzli1.lifecycletest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by hzli1 on 2018/7/8.
 */

public class MyService extends Service{
    boolean flag=true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("dddd","Service oncreate");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag){
                    long tt=System.currentTimeMillis();
                    Log.i("dddd","Service Thread:"+tt);
                    Intent intent =new Intent();
                    intent.setAction("edu.freshen.broadcast");

                    intent.putExtra("data",tt+"");
                    sendBroadcast(intent);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("dddd","Service ondestroy");
        flag=false;
    }
}
