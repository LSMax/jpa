package com.example.jpa.control.common;

import com.alibaba.fastjson.JSON;

public class ApplicationError {
    //必要的提示信息
    private String message;
    //业务数据
    private Object data;

    public ApplicationError(String message,Object data){
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String toString(){
        if(null == this.data){
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }
}
