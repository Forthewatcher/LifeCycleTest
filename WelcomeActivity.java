package com.example.hzli1.lifecycletest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hzli1.lifecycletest.myAdapter.GetUrlResource;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et;
    private Button btsave,btread,btdownload;
    FileOutputStream fos;
    FileInputStream fis;
    TextView tv;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x300:
                    Toast.makeText(WelcomeActivity.this,"下载完成",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        et=(EditText)findViewById(R.id.et00);
        btsave=(Button) findViewById(R.id.btsave);
        btread=(Button) findViewById(R.id.btread);
        btdownload=(Button) findViewById(R.id.btdownload);
        tv=(TextView)findViewById(R.id.tv00);
        btsave.setOnClickListener(this);
        btread.setOnClickListener(this);
        btdownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btsave:
                saveData();
                break;
            case R.id.btread:
                readData();
                break;
            case R.id.btdownload:
                loadData();
                break;
        }

    }

    private void loadData() {
        GetUrlResource.getResourceBy("http://www.cauc.edu.cn/zh/pic21.jpg",System.currentTimeMillis()+".jpg",this,handler);
//        new Thread(new Runnable() {
//            InputStream is=null;
//            FileOutputStream fos=null;
//            @Override
//            public void run() {
//                String urlstr="http://www.cauc.edu.cn/zh/pic21.jpg";
//                try {
//                    URL url=new URL(urlstr);
//                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
//                    is=conn.getInputStream();
//                    fos=openFileOutput(System.currentTimeMillis()+".jpg",Context.MODE_PRIVATE);
//                    byte buff[]=new byte[4*1024];
//                    int count=0;
//                    while((count=is.read(buff))>0){
//                        fos.write(buff,0,count);
//                    }
//                    fos.flush();
//                    handler.sendEmptyMessage(0x300);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }finally {
//                    if(is!=null) try {
//                        is.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    if(fos!=null) try {
//                        fos.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();


    }


    private void readData() {
        tv.setText("");
        BufferedReader br=null;
        try {
            fis=openFileInput("fairy.dat");
            br=new BufferedReader(new InputStreamReader(fis));
            String ln;
            while((ln=br.readLine())!=null){
                tv.append(ln+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null) try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveData(){

        PrintWriter pw=null;
        try {
            fos=openFileOutput("fairy.dat", Context.MODE_APPEND);
            pw=new PrintWriter(fos);
            pw.println(et.getText().toString());
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(pw!=null)pw.close();
            if(fos!=null) try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        et.setText("");
    }


}
