package com.example.hzli1.lifecycletest.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hzli1 on 2018/7/8.
 */

public class MyReceiver extends BroadcastReceiver {
    MyServiceActivity msa;

    public MyReceiver(MyServiceActivity msa) {
        super();
        this.msa = msa;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String data=intent.getStringExtra("data");
        Log.i("dddd","收到广播"+data);
        msa.setText(data);
    }
}
