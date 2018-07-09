package com.example.hzli1.lifecycletest.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hzli1 on 2018/7/7.
 */

public class Result {
    private int state=200;
    private String msg="ok";
    private List<Map<String,String>> data=new ArrayList<Map<String, String>>();
    public Result(){
        super();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }


}
