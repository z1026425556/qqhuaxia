package com.pengcheng.service.impl;

import com.pengcheng.dao.SignInMapper;
import com.pengcheng.dao.WatchLinkmanMapper;
import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.SignIn;
import com.pengcheng.domain.WatchLinkman;
import com.pengcheng.domain.auth.User;
import com.pengcheng.service.IShiroService;
import com.pengcheng.service.ISigninService;
import com.pengcheng.service.IWatchLinkmanService;
import com.pengcheng.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class SigninServiceImpl implements ISigninService {

    @Autowired
    IShiroService shiroService;

    @Autowired
    SignInMapper signInMapper;

    @Autowired
    WatchLinkmanMapper watchLinkmanMapper;

    @Autowired
    IWatchLinkmanService watchLinkmanService;

    @Override
    public ModelData sign(HttpServletRequest httpRequest) {
        //返回的数据：1、剩余查看次数。2、本月累计签到天数。3、今日是否已签到
        ModelData result = new ModelData();
        Map<String, Object> data = new HashMap<String, Object>();
        User user = shiroService.findByAccessToken(httpRequest.getHeader("token"));
        SignIn signIn = signInMapper.findByUserId(user.getId());
        if(signIn == null){
            signIn = new SignIn();
            signIn.setUserId(user.getId());
            signIn.setMonthCount(1);
            signIn.setUpdateTime(LocalDateTime.now());
            signInMapper.addOne(signIn);
            data.put("watchCount", 0);
            data.put("monthCount", 1);
        }else{
            //判断用户是否今天已经签到过了，点击重复或者黑客利用漏洞进行多次签到
            if(signIn.getUpdateTime().isAfter(LocalDateTime.of(LocalDate.from(LocalDateTime.now()), LocalTime.MIN))){
                result.code = "420";
                result.msg = "签到失败！今天已经进行过签到了！";
                return result;
            }
            //获取当月第一天0时的时间
            LocalDateTime mouthFirstTime = LocalDateTime.of(LocalDate.from(LocalDateTime.now().withDayOfMonth(1)), LocalTime.MIN);
            WatchLinkman watchLinkman = watchLinkmanMapper.findByUserId(user.getId());
            if(signIn.getUpdateTime().isBefore(mouthFirstTime)){
                signIn.setMonthCount(1);
            }else{
                signIn.setMonthCount(signIn.getMonthCount() + 1);
                //判断签到时间是否是3/7/15/20/25
                int[] signInDay = new int[]{3, 7, 15, 20, 25};
                if(ArrayUtil.intArrLookupInt(signInDay, signIn.getMonthCount())){
                    if(watchLinkman == null){
                        watchLinkman = new WatchLinkman();
                        watchLinkman.setUserId(user.getId());
                        watchLinkman.setWatchCount(1);
                    }else{
                        watchLinkman.setWatchCount(watchLinkman.getWatchCount() + 1);
                    }
                    watchLinkmanService.save(watchLinkman);
                }
            }
            signIn.setUpdateTime(LocalDateTime.now());
            signInMapper.updateOne(signIn);
            data.put("watchCount", watchLinkman == null ? 0 : watchLinkman.getWatchCount());
            data.put("monthCount", signIn.getMonthCount());
        }
        result.code = "200";
        result.msg = "签到成功！";
        result.data = data;
        return result;
    }

}
