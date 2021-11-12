package com.pengcheng.service;


import com.pengcheng.domain.PayCallback;

public interface IPayService {

    void insertPayOrder(PayCallback order);

    String querySuccessWX(String zzfOId);

    String queryWX(Long oId);

}
