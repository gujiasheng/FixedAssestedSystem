package com.gjs.fixedassets.entity;


/*
 * @Description TODO
 * 返回json的
 * @Author 顾嘉晟
 * @Date 2021-02-19
 *
 **/

public class Result {
    private String code;
    private String msg;
    private long count = 0;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long l) {
        this.count = l;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
