package com.pengcheng.service.impl;

import com.pengcheng.dao.PayMapper;
import com.pengcheng.dao.VipMapper;
import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.Pay;
import com.pengcheng.domain.PayCallback;
import com.pengcheng.domain.Vip;
import com.pengcheng.domain.auth.User;
import com.pengcheng.service.IPayService;
import com.pengcheng.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayServiceImpl implements IPayService {

    @Autowired
    private IShiroService shiroService;

    @Autowired
    private PayMapper payMapper;

    @Autowired
    private VipMapper vipMapper;

    @Override
    public void createPayRecord(PayCallback payCallback) {
        Pay pay = new Pay();
        pay.setUserId(Long.valueOf(payCallback.getThirduid()));
        pay.setZzfId(payCallback.getOrderId());
        pay.setPrice(Double.valueOf(payCallback.getPrice()));
        pay.setOriginalPrice(Double.valueOf(payCallback.getOriginalprice()));
        pay.setDescription(payCallback.getName());
        payMapper.addOne(pay);
        payMapper.addLog(pay);
        Vip vip = vipMapper.findByUserId(pay.getUserId());
        if(vip == null){
            vip.setType("0");
            vip.setDescription("ζε‘δΌε");
            vip.setUserId(pay.getUserId());
            vip.setBuyTime(LocalDateTime.now());
            vip.setExpireTime(LocalDateTime.now().plusDays(30));
            vipMapper.updateOne(vip);
        }else{
            vipMapper.addOne(vip);
        }
    }

    @Override
    public ModelData queryPayResult(HttpServletRequest httpRequest) {
        ModelData result = new ModelData();
        User user = shiroService.findByAccessToken(httpRequest.getHeader("token"));
        Pay pay = payMapper.findByUserId(user.getId());
        if(pay == null){
            result.code = "500";
            result.msg = "ζͺη₯εΌεΈΈοΌθ―·θη³»η?‘ηεοΌ";
        }else{
            result.code = "200";
            result.msg = "ζ―δ»ζεοΌ";
            Vip vip = vipMapper.findByUserId(user.getId());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("vipExpireTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(vip.getExpireTime()));
            if(vip.getType().equals("1")){
                data.put("type", "ζε‘δΌε");
            }else if(vip.getType().equals("2")){
                data.put("type", "ε­£ε‘δΌε");
            }else if(vip.getType().equals("3")){
                data.put("type", "εΉ΄ε‘δΌε");
            }
            result.data = data;
            payMapper.deleteById(pay.getId());
        }
        return result;
    }



}











































