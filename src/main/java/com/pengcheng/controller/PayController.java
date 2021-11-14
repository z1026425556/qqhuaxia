package com.pengcheng.controller;

import com.alibaba.fastjson.JSONObject;
import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.PayCallback;
import com.pengcheng.service.IPayService;
import com.pengcheng.service.IShiroService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pay")
public class PayController {

    public static String appKey = "707debec875c4027";
    public static String appSecret = "c3b795d700334821b169849647e97982";
    //统一下单地址
    public static String createOrderURL = "https://admin.zhanzhangfu.com/order/createOrder";
    //订单查询地址
//    public static String findOrderURL = "https://admin.zhanzhangfu.com/order/onlinePayFindResult";

    //会员价格
    private static Integer vipPrice = 38;

    //查看价格
    private static Integer watchPrice = 1;

    @Autowired
    private IPayService payService;

    @Autowired
    private IShiroService shiroService;

    @RequestMapping("/callback")
    public void callback(@RequestBody PayCallback payCallback){
        payService.createPayRecord(payCallback);
    }


    @RequestMapping("/createVipMonthOrder")
    public ModelData createVipMonthOrder(HttpServletRequest httpRequest){

        String res = "";
        JSONObject jsonObject = null;
        ModelData result = new ModelData();

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
        postMethod.addParameter("price", String.valueOf(vipPrice));
        postMethod.addParameter("name", "开通月卡会员！");
//        postMethod.addParameter("callbackurl", "用来通知指定地址");
//        postMethod.addParameter("reurl", "跳转地址");
        postMethod.addParameter("thirduid", String.valueOf(shiroService.findByAccessToken(httpRequest.getHeader("token")).getId()));
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
                    result.code = "200";
                    result.msg = "创建订单成功！";
                    result.data = jsonObject.getString("price");
                }else{
                    result.code = "500";
                    result.msg = "未知异常，请联系管理员！";
                }
            }

        }catch(Exception e){
            result.code = "500";
            result.msg = "未知异常，请联系管理员！";
        }

        return result;
    }

    @RequestMapping("/createWatchLinkmanOrder")
    public ModelData createWatchLinkmanOrder(){

        String res = "";
        JSONObject jsonObject = null;
        ModelData result = new ModelData();

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
        postMethod.addParameter("price", String.valueOf(watchPrice));
        postMethod.addParameter("name", "1元查看联系方式！");
//        postMethod.addParameter("callbackurl", "用来通知指定地址");
//        postMethod.addParameter("reurl", "跳转地址");
//        postMethod.addParameter("thirduid", oId);
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
                    result.code = "200";
                    result.msg = "创建订单成功！";
                }else{
                    result.code = "500";
                    result.msg = "未知异常，请联系管理员！";
                }
            }

        }catch(Exception e){
            result.code = "500";
            result.msg = "未知异常，请联系管理员！";
        }

        return result;
    }

    @RequestMapping("queryPayResult")
    public ModelData queryPayResult(HttpServletRequest httpRequest){
        return payService.queryPayResult(httpRequest);
    }

}













































