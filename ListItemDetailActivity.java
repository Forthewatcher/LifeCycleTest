package com.example.hzli1.lifecycletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ListItemDetailActivity extends AppCompatActivity {
    TextView bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_detail);
        Intent intent=getIntent();
        String bname=intent.getStringExtra("BookID");
        bt=(TextView)findViewById(R.id.itemdetail);
        bt.setText(bname);
    }
}
