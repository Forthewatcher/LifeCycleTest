package com.example.hzli1.lifecycletest;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt1,bt2,bt3,bt4,bt5;
    private String[] data={"bj","bb","cc","dd"};
    private List<String> cdata=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        bt1=(Button)findViewById(R.id.button7);
        bt2=(Button)findViewById(R.id.button8);
        bt3=(Button)findViewById(R.id.button9);
        bt4=(Button)findViewById(R.id.button10);
        bt5=(Button)findViewById(R.id.button11);
        bt5.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button7:
                openCommDialog();
                break;
            case R.id.button8:
                openListDaialog();
                break;
            case R.id.button9:
                openCheckDialog();
                break;
            case R.id.button10:
                openDateDialog();
                break;
            case R.id.button11:
                openUserDialog();
                break;
        }

    }

    private void openUserDialog() {
        AlertDialog.Builder bld=new AlertDialog.Builder(this);
        bld.setTitle("message!");
       final View v=LayoutInflater.from(this).inflate(R.layout.dialog_login,null);
        bld.setView(v);
        bld.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et1=(EditText)v.findViewById(R.id.dialog_name_in);
                EditText et2=(EditText)v.findViewById(R.id.dialog_pass_in);
                String name=et1.getText().toString();
                String pass=et2.getText().toString();
                Toast.makeText(getApplicationContext(),"User"+name+"<-->"+pass,Toast.LENGTH_SHORT).show();

            }
        });
        bld.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
            }
        });
        bld.show();

    }

    private void openDateDialog() {
        Calendar c=Calendar.getInstance();
        DatePickerDialog dpd=new DatePickerDialog(DialogActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(),"你选择的日期是"+year+"-"+(month+1)+"-"+dayOfMonth,Toast.LENGTH_LONG).show();
            }
        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        dpd.show();
    }

    public void openCheckDialog(){
        cdata.clear();
        AlertDialog.Builder bld=new AlertDialog.Builder(this);
        bld.setIcon(R.drawable.wf01);
        bld.setTitle("message!");
        boolean bs[]=new boolean[data.length];
        Arrays.fill(bs,false);
        bld.setMultiChoiceItems(data, bs, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    cdata.add(data[which]);
                }else{
                    cdata.remove(data[which]);
                }
            }
        });
        bld.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"你选择的是"+cdata.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        bld.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
            }
        });
        bld.show();
    }
    private void openListDaialog(){
        AlertDialog.Builder bld=new AlertDialog.Builder(this);
        bld.setIcon(R.drawable.wf01);
        bld.setTitle("message!");
        bld.setItems(data, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"you choose "+data[which],Toast.LENGTH_SHORT).show();
            }
        });
        bld.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
            }
        });
        bld.show();
    }
    private void openCommDialog(){
        AlertDialog.Builder bld=new AlertDialog.Builder(this);
        bld.setTitle("message!");
        bld.setMessage("Sure?");
        bld.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_SHORT).show();

            }
        });
        bld.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
            }
        });
        bld.show();

    }
}
