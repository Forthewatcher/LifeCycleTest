package com.example.hzli1.lifecycletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.hzli1.lifecycletest.myAdapter.BookItemAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    List<Map<String,Object>> listdata=new ArrayList<Map<String,Object>>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        lv=(ListView)findViewById(R.id.listview);
        intlv();
    }

    private void intlv() {
        for (int i = 0; i <20 ; i++) {
            Map<String,Object> item=new HashMap<String,Object>();
            item.put("name","书名"+i);
            if(i%3==0){
                item.put("img1",R.drawable.wf06);
            }else{
                item.put("img1",R.drawable.ic_launcher);
            }
            item.put("price","￥"+(10+i*2.5));
            item.put("pro",i%6);
            listdata.add(item);
        }
//        ArrayAdapter ba=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listdata);
//        SimpleAdapter sa=new SimpleAdapter(this,listdata,R.layout.layout_item,new String[]{"img1","name","price"}
//        ,new int[]{R.id.listview_item_image1,R.id.listview_item_name,R.id.textViewPrice});
        BookItemAdapter ba=new BookItemAdapter(this,listdata,R.layout.layout_item);
        lv.setAdapter(ba);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = listdata.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("BookID", (item.get("name")).toString());//正常情况下把数据传入数据库，传ID给另一个AT，他再取数据库信息
                Intent intent = new Intent(ListViewActivity.this, ListItemDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
