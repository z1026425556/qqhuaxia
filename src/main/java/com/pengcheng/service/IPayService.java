package com.pengcheng.service;

import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.PayCallback;

import javax.servlet.http.HttpServletRequest;

public interface IPayService {

    void createPayRecord(PayCallback payCallback);

    ModelData queryPayResult(HttpServletRequest httpRequest);

}
