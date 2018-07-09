package com.example.hzli1.lifecycletest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

public class HandlerActivity extends AppCompatActivity {
    ProgressBar pb;
    boolean flag=true;
    Random rand=new Random();
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 100:
                    pb.setProgress(pb.getProgress()+rand.nextInt(10));
                    break;
                case 200:
                    Toast.makeText(HandlerActivity.this,"下载完成",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        pb= (ProgressBar) findViewById(R.id.progressBar);
    }
    public void download(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    if (pb.getProgress() >= pb.getMax()) break;
                    Message msg = new Message();
                    handler.sendEmptyMessage(100);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(200);
            }
        }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flag=false;
    }
}
