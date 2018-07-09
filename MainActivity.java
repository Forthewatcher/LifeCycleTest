package com.example.hzli1.lifecycletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static String CLASS_NAME="test";
    private Button mail,mail2,listview,bt2,lvbt,dsbt,dbbt,handlerbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linerlay);
        bt2=(Button)findViewById(R.id.button2);
        bt2.setOnClickListener(this);
        dsbt=(Button)findViewById(R.id.button13);
        dsbt.setOnClickListener(this);
        lvbt=(Button)findViewById(R.id.listview_botton);
        lvbt.setOnClickListener(this);
        mail=(Button)findViewById(R.id.button4);
        mail.setOnClickListener(this);
        mail2=(Button)findViewById(R.id.button5);
        mail2.setOnClickListener(this);
        dbbt= (Button) findViewById(R.id.button14);
        dbbt.setOnClickListener(this);
        handlerbt= (Button) findViewById(R.id.button20);
        handlerbt.setOnClickListener(this);
//        mail2=(Button)findViewById(R.id.listview_botton);
//        mail2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,ListViewActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    protected void startThird(View v){
        Intent intent=new Intent(MainActivity.this,ThirdActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(CLASS_NAME, "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(CLASS_NAME, "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(CLASS_NAME, "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(CLASS_NAME, "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(CLASS_NAME, "onDestroy");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(CLASS_NAME, "onRestart");
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.button2:
                    intent=new Intent(MainActivity.this,DialogActivity.class);
                    startActivity(intent);
                break;
            case R.id.button13:
                intent=new Intent(MainActivity.this,DataSaveActivity.class);
                startActivity(intent);
                break;
            case R.id.listview_botton:
                intent=new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.button4:
                intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.button5:
                intent=new Intent(MainActivity.this,LoginActivity2.class);
                startActivity(intent);
                break;
            case R.id.button14:
                intent=new Intent(MainActivity.this,DBActivity.class);
                startActivity(intent);
            case R.id.button20:
                intent=new Intent(MainActivity.this,HandlerActivity.class);
                startActivity(intent);

        }
    }
}
