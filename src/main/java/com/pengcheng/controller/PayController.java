package com.pengcheng.controller;

import com.alibaba.fastjson.JSONObject;
import com.pengcheng.domain.PayCallback;
import com.pengcheng.domain.PayOrder;
import com.pengcheng.service.IPayService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {

    public static String appKey = "707debec875c4027";
    public static String appSecret = "c3b795d700334821b169849647e97982";
    //统一下单地址
    public static String createOrderURL = "https://admin.zhanzhangfu.com/order/createOrder";
    //订单查询地址
    public static String findOrderURL = "https://admin.zhanzhangfu.com/order/onlinePayFindResult";

    private static Integer price = 1;

    @Autowired
    private IPayService payService;

    @RequestMapping("/callback")
    public void callback(@RequestBody PayCallback p){
        System.out.println("调用了callback方法");
        payService.insertPayOrder(p);
    }

    @RequestMapping("/querySuccessWX")
    public String querySuccessWX(String zzfOId){
        String result = "wait";
        String temp = payService.querySuccessWX(zzfOId);
        if(!"".equals(temp) && null != temp){
            result = temp;
        }
        return  result;
    }

    @RequestMapping("/queryWX")
    public String queryWX(Long oId){
        return payService.queryWX(oId);
    }

    @RequestMapping("/createOrder")
    public PayOrder createOrder(String name, String oId){

        String res = "";
        JSONObject jsonObject = null;
        PayOrder result = new PayOrder();

        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(createOrderURL);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
        //设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(5000);
        //设置读取数据超时时间(单位毫秒)
        managerParams.setSoTimeout(5000);
        //头信息
        postMethod.setRequestHeader("Payment-Key", appKey);
        postMethod.setRequestHeader("Payment-Secret", appSecret);
        postMethod.addParameter("price", String.valueOf(price));
        postMethod.addParameter("name", name);
//        postMethod.addParameter("callbackurl", "用来通知指定地址");
//        postMethod.addParameter("reurl", "跳转地址");
        postMethod.addParameter("thirduid", oId);
//        postMethod.addParameter("remarks", "备注");
//        postMethod.addParameter("other", "其他信息");
        try{

            System.out.println(postMethod.getURI());
            int code = client.executeMethod(postMethod);
            if(code == 200){
                res = postMethod.getResponseBodyAsString();
                System.out.println(res);
                jsonObject = JSONObject.parseObject(res);
                if(jsonObject.getString("code").equals("10001")){
                    result.setCode(200);
                    result.setName(jsonObject.getString("name"));
                    result.setPrice(jsonObject.getDouble("price"));
                    result.setZzfOId(jsonObject.getString("orderId"));
                }else{
                    result.setCode(500);
                    result.setName(jsonObject.getString("name"));
                    result.setPrice(jsonObject.getDouble("price"));
                    result.setInfo("未知异常，请联系管理员");
                }
            }

        }catch(Exception e){
            result.setInfo("未知异常，请联系管理员");
        }

        return result;
    }

}













































