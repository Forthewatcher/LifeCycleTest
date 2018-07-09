package com.example.hzli1.lifecycletest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et1,et2;
    private Button b1,b2,b3,b4;
    private ListView lv;
    private TextView tv;
    SQLiteDatabase db;
    DBHelper dbHelper;
    List<Map<String,Object>> data=new ArrayList() ;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        et1=(EditText)findViewById(R.id.editTextID);
        et2= (EditText) findViewById(R.id.editTextSname);
        lv= (ListView) findViewById(R.id.QueryShow);
        tv= (TextView) findViewById(R.id.textView4);
        b1= (Button) findViewById(R.id.button15);
        b2= (Button) findViewById(R.id.button16);
        b3= (Button) findViewById(R.id.button17);
        b4= (Button) findViewById(R.id.button18);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        dbHelper=new DBHelper(getApplicationContext(),"studb",null,1);
        db=dbHelper.getWritableDatabase();
        intitlv();
    }

    private void intitlv() {
        sa=new SimpleAdapter(this,data,R.layout.db_listview_item,
                new String[]{"sid","sname"},new int[]{R.id.db_listview_item_id,R.id.db_listview_item_name});
        lv.setAdapter(sa);
    }

    @Override
    public void onClick(View v) {
        String id=et1.getText().toString();
        String name=et2.getText().toString();
        switch (v.getId()){
            case R.id.button15:
                saveData(id,name);
                break;
            case R.id.button16:
                deleteData(id);
                break;
            case R.id.button17:
                queryAllData();
                break;
            case R.id.button18:
                updateData(id,name);
                break;

        }
    }

    private void updateData(String id, String name) {
        ContentValues cv=new ContentValues();
        cv.put("sname",name);
        long r=db.update("studentInfo",cv,"sid=?",new String[]{id});
        Toast.makeText(this,"更新成功，"+r,Toast.LENGTH_SHORT).show();
    }

    private void queryAllData() {
        data.clear();
        tv.setText("");
        Cursor c=db.query("studentInfo",new String[]{"sid","sname"},null,null,null,null,"sid asc");

        while(c.moveToNext()){
            String id=c.getString(c.getColumnIndex("sid"));
            String name=c.getString(c.getColumnIndex("sname"));
            tv.append("id:"+id+",name:"+name+"\n");
            Map<String,Object> item=new HashMap();
            item.put("sid",id);
            item.put("sname",name);
            data.add(item);
        }
        c.close();
        sa.notifyDataSetChanged();//更新数据
    }

    private void deleteData(String id) {
        long r=db.delete("studentInfo","sid=?",new String[]{id});
        Toast.makeText(this,"删除成功,"+r,Toast.LENGTH_SHORT).show();
    }

    private void saveData(String id, String name) {
        ContentValues cv=new ContentValues();
        cv.put("sid",id);
        cv.put("sname",name);
        long r=db.insert("studentInfo",null,cv);
        Toast.makeText(this,"插入成功,"+r,Toast.LENGTH_SHORT).show();
    }
}
