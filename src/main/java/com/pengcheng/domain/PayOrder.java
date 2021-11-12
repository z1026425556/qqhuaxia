package com.pengcheng.domain;

public class PayOrder {

    private Integer code;
    private Double price;
    private String name;
    private String info;
    private String zzfOId;

    public String getZzfOId() {
        return zzfOId;
    }

    public void setZzfOId(String zzfOId) {
        this.zzfOId = zzfOId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
