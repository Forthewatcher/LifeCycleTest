package com.example.hzli1.lifecycletest.xutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzli1.lifecycletest.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MyxutilsActivity extends AppCompatActivity {
    @ViewInject(R.id.textView6)
    private TextView tv;
    @ViewInject(R.id.button22)
    private Button lw;
    @ViewInject(R.id.button24)
    private Button tp;
    @ViewInject(R.id.imageView)
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myxutils);
        x.view().inject(this);
    }
    //监听
    @Event(value=R.id.button24,type= View.OnClickListener.class)
    private void connPicture(View v){
        String urlStr="http://www.005.tv/uploads/allimg/180705/10530V426-11.jpg";
        ImageOptions ip=new ImageOptions.Builder().setLoadingDrawableId(R.drawable.ic_launcher)
                .setFailureDrawableId(R.drawable.wf06)
                .setImageScaleType(ImageView.ScaleType.CENTER_INSIDE)
                .build();
        x.image().bind(iv,urlStr,ip);

    }
    @Event(value={R.id.button22},type= View.OnClickListener.class)
    private void connWeb(View v){
        String urlStr="http://10.8.34.20:8080/fly/server/login.do";
        RequestParams rp=new RequestParams(urlStr);
        rp.addParameter("name","admin");
        rp.addParameter("pwd","admin");

        x.http().get(rp, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i("dddd","服务器返回"+result);
                tv.setText("服务器返回"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
