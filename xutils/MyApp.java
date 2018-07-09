package com.example.hzli1.lifecycletest.xutils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by hzli1 on 2018/7/8.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
