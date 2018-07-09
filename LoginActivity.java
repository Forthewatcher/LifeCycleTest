package com.example.hzli1.lifecycletest;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.hzli1.lifecycletest.Data.Result;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    String rootUrl;
    Application app;
    EditText et1,et2;
    Button b1,b2;
    ListView lv;
    SimpleAdapter sa;
    Result result=new Result();
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    b2.setEnabled(true);
                    break;
                case 201:
                    sa.notifyDataSetChanged();
                    break;
                default:
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rootUrl=App.rootUrlStr;
        et1= (EditText) findViewById(R.id.editText4);
        et2= (EditText) findViewById(R.id.editText5);
        b1= (Button) findViewById(R.id.button4);
        b2= (Button) findViewById(R.id.button21);
        lv= (ListView) findViewById(R.id.book_listview);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b2.setEnabled(false);

        initlv();
    }

    private void initlv() {
        sa=new SimpleAdapter(this, result.getData(),R.layout.layout,new String[]
                {"bookId","bookName","bookPrice"},new int[]{R.id.book_id,R.id.book_name,R.id.book_price});
        lv.setAdapter(sa);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button4:
                String n=et1.getText().toString();
                String p=et2.getText().toString();
                login(n,p);
                break;
            case R.id.button21:
                loadBooks();
                break;
        }
    }

    private void loadBooks() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                try {
                    String urlstr=rootUrl+"server/booklist.do";
                    URL url=new URL(urlstr);
                    conn= (HttpURLConnection) url.openConnection();
                    br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sbuf = new StringBuffer();
                    String ln;
                    while((ln=br.readLine())!=null){
                        sbuf.append(ln);
                    }
                    Log.i("dddd","服务器返回"+sbuf.toString());
                    //解析Json串
                    Gson gson=new Gson();
                    Result r=gson.fromJson(sbuf.toString(),Result.class);
                    result.getData().addAll(r.getData());
                    handler.sendEmptyMessage(201);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void login(final String n, final String p) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                try {
                    String urlstr=rootUrl+"server/login.do?name="+n+"&pwd="+p;
                    URL url=new URL(urlstr);
                    conn= (HttpURLConnection) url.openConnection();
                    br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sbuf = new StringBuffer();
                    String ln;
                    while((ln=br.readLine())!=null){
                        sbuf.append(ln);
                    }
                    Log.i("dddd","服务器返回"+sbuf.toString());
                    if(sbuf.toString().contains("success")){

                        handler.sendEmptyMessage(200);
                    }else{
                        handler.sendEmptyMessage(100);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
