package com.example.hzli1.lifecycletest.tongzhilancamera;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.hzli1.lifecycletest.R;

import java.io.File;
import java.util.Random;

public class TZLCameraActivity extends AppCompatActivity {
    ImageView iv;
    Random r =new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tzlcamera);
        iv=(ImageView) findViewById(R.id.picture);
    }
    //发通知
    public void sendNotify(View v){

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder bld =new Notification.Builder(this);
        bld.setSmallIcon(R.drawable.ic_launcher);
        bld.setTicker("来信息了");
        bld.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        bld.setContentTitle("接机提醒：");
        bld.setContentText("ZH8005航班即将到达T2航站楼，请查阅.");

        //用户点击通知后跳转
        Intent intent =new Intent();
        intent.setAction("edu.freshen.msg.ACTION");
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        PendingIntent pi =PendingIntent.getActivity(this,
                100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        bld.setContentIntent(pi);
        Notification n = bld.build();


        nm.notify(r.nextInt(100), n);


    }
    File file;
    //拍照
    public void takePic(View v){
        Intent intent =new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        File sdRoot = Environment.getExternalStorageDirectory();
        file=new File(sdRoot,System.currentTimeMillis()+".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, 100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            //Bitmap bit =(Bitmap) data.getExtras().get("data");
            Bitmap bit = BitmapFactory.decodeFile(file.getAbsolutePath());
            Log.i("Msg", "字节数："+bit.getByteCount());
            iv.setImageBitmap(bit);
        }

    }
}
