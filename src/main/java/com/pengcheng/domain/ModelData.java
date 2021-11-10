package com.pengcheng.domain;

//返回给前端的数据模型
public class ModelData {

    public String code;     //200：成功，500：失败
    public String msg;      //信息
    public Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
