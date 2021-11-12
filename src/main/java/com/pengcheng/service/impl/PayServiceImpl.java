package com.pengcheng.service.impl;

import com.pengcheng.dao.PayOrderMapper;
import com.pengcheng.domain.PayCallback;
import com.pengcheng.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements IPayService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Override
    public void insertPayOrder(PayCallback order) {
        payOrderMapper.insertPayOrder(order);
    }

    @Override
    public String querySuccessWX(String zzfOId) {
        return payOrderMapper.querySuccessWX(zzfOId);
    }

    @Override
    public String queryWX(Long oId) {
        return payOrderMapper.queryWX(oId);
    }

}
