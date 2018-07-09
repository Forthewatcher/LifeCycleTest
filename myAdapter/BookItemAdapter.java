package com.example.hzli1.lifecycletest.myAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hzli1.lifecycletest.R;

import java.util.List;
import java.util.Map;

/**
 * Created by hzli1 on 2018/7/4.
 */

public class BookItemAdapter extends BaseAdapter {
    private Activity act;
    List<Map<String,Object>> data;
    private int layout;

    public BookItemAdapter(Activity act, List<Map<String, Object>> data, int layout) {
        this.act = act;
        this.data = data;
        this.layout = layout;
    }

    @Override

    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lf=LayoutInflater.from(act);
        View view;
        if(convertView==null) {
            view = lf.inflate(layout, null);
        }else{
            view =convertView;
        }
        ImageView iv=(ImageView)view.findViewById(R.id.listview_item_image1);
        TextView name=(TextView)view.findViewById(R.id.listview_item_name);
        TextView price=(TextView)view.findViewById(R.id.textViewPrice);
        RatingBar rb=(RatingBar)view.findViewById(R.id.ratingBar);
        Button bt=(Button)view.findViewById(R.id.button6);
        final Map<String, Object> item=data.get(position);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(act,"你打算购买："+item.get("name"),Toast.LENGTH_SHORT).show();
            }
        });

        iv.setImageResource(Integer.parseInt(item.get("img1").toString()));
        name.setText(item.get("name").toString());
        price.setText(item.get("price").toString());
        rb.setProgress(Integer.parseInt(item.get("pro").toString()));
        return view;
    }
}
