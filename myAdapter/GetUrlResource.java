package com.example.hzli1.lifecycletest.myAdapter;

import android.content.Context;
import android.os.Handler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hzli1 on 2018/7/5.
 */

public  class GetUrlResource {
    public static void getResourceBy(final String urlstr, final String name, final Context context, final Handler handler){
        new Thread(new Runnable() {
            InputStream is=null;
            FileOutputStream fos=null;
            @Override
            public void run() {
                try {
                    URL url=new URL(urlstr);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    is=conn.getInputStream();
                    fos=context.openFileOutput(name, Context.MODE_PRIVATE);
                    byte buff[]=new byte[4*1024];
                    int count=0;
                    while((count=is.read(buff))>0){
                        fos.write(buff,0,count);
                    }
                    fos.flush();
                    handler.sendEmptyMessage(0x300);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(is!=null) try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(fos!=null) try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
