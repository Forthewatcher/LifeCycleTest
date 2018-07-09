package com.example.hzli1.lifecycletest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ThirdActivity extends AppCompatActivity implements OnSeekBarChangeListener{

    private SeekBar sb1,sb2,sb3;
    private ConstraintLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        sb1=(SeekBar)findViewById(R.id.seekBar1);
        sb2=(SeekBar)findViewById(R.id.seekBar2);
        sb3=(SeekBar)findViewById(R.id.seekBar3);
        ll=(ConstraintLayout) findViewById(R.id.view1);
        sb1.setOnSeekBarChangeListener(this);
        sb2.setOnSeekBarChangeListener(this);
        sb3.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int r=(int)(sb1.getProgress()/100.0 * 255);
        int g=(int)(sb2.getProgress()/100.0 * 255);
        int b=(int)(sb3.getProgress()/100.0 * 255);
        ll.setBackgroundColor(Color.rgb(r,g,b));
        Log.d("test","color:"+r+g+b);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
