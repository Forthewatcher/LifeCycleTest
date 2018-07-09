package com.example.hzli1.lifecycletest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DataSaveActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et1,et2;
    CheckBox cb;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_save);
        checkLogin();
        et1=(EditText)findViewById(R.id.editText2);
        et2=(EditText)findViewById(R.id.editText3);
        cb=(CheckBox)findViewById(R.id.checkBox2);
        bt=(Button) findViewById(R.id.button12);
        bt.setOnClickListener(this);
    }

    private void checkLogin() {
        SharedPreferences spf=getSharedPreferences("data", MODE_PRIVATE);
//        SharedPreferences.Editor e=spf.edit();
        String name=spf.getString("name","");
        if(!name.equals("")){
            startActivity(new Intent(DataSaveActivity.this,WelcomeActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        if (cb.isChecked()){
            String name=et1.getText().toString();
            String pwd=et2.getText().toString();
            saveData(name,pwd);
        }
        startActivity(new Intent(DataSaveActivity.this,WelcomeActivity.class));
    }

    private void saveData(String name, String pwd) {
        SharedPreferences spf=getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor e=spf.edit();
        e.putString("name",name);
        e.putString("pwd",pwd);
        e.commit();
        Log.i("test","保存成功");
    }

}
