package com.pengcheng.service.impl;

import com.pengcheng.dao.*;
import com.pengcheng.dao.auth.UserMapper;
import com.pengcheng.domain.*;
import com.pengcheng.domain.auth.User;
import com.pengcheng.service.IShiroService;
import com.pengcheng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private InviteCodeMapper inviteCodeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IShiroService shiroService;

    @Autowired
    private VipMapper vipMapper;

    @Autowired
    private BannedMapper bannedMapper;

    @Autowired
    private WatchLinkmanMapper watchLinkmanMapper;

    @Autowired
    private SignInMapper signInMapper;

    @Override
    public InviteCode findByCode(String inviteCode) {
        return inviteCodeMapper.findByCode(inviteCode);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public ModelData register(User user, String inviteCode) {

        ModelData result = new ModelData();
        user.setStatus("0");
        userMapper.addOne(user);
        Map<String, Object> tokenMap = shiroService.createToken(user.getId());
        inviteCodeMapper.deleteByCode(inviteCode);
        result.code = "200";
        result.msg = "注册成功！";
        result.data = tokenMap;

        return result;
    }

    @Override
    public ModelData login(User inputUser) {
        ModelData result = new ModelData();
        User user = userMapper.findByUsername(inputUser.getUsername());
        if(user == null){
            result.code = "411";
            result.msg = "用户不存在！";
            return result;
        }
        if(!user.getPassword().equals(inputUser.getPassword())){
            result.code = "412";
            result.msg = "密码错误！";
            return result;
        }
        Map<String, Object> token = shiroService.createToken(user.getId());
        result.code = "200";
        result.msg = "登录成功";
        result.data = token;
        return result;
    }

    @Override
    public ModelData resetPwd(User inputUser, String resetPwd) {
        ModelData result = new ModelData();
        User user = userMapper.findByUsername(inputUser.getUsername());
        if(user == null){
            result.code = "411";
            result.msg = "用户不存在！";
            return result;
        }
        if(!user.getSuperPwd().equals(inputUser.getSuperPwd())){
            result.code = "413";
            result.msg = "密保不正确！";
            return result;
        }
        if(!inputUser.getPassword().equals(resetPwd)){
            result.code = "414";
            result.msg = "两次输入的密码不一致！";
            return result;
        }
        user.setPassword(inputUser.getPassword());
        userMapper.updateOne(user);
        Map<String, Object> tokenData = shiroService.createToken(user.getId());
        result.code = "200";
        result.msg = "密码重置成功！";
        result.data = tokenData;
        return result;
    }

    @Override
    public ModelData queryPersonalInfo(HttpServletRequest httpRequest) {
        ModelData result = new ModelData();
        PersonalInfo data = new PersonalInfo();
        User user = shiroService.findByAccessToken(httpRequest.getHeader("token"));
        data.nickname = user.getNickname();
        data.username = user.getUsername();
        Vip vip = vipMapper.findByUserId(user.getId());
        if(vip == null){
            data.type = "普通用户";
        }else if(vip.getType().equals("0") && vip.getExpireTime().isAfter(LocalDateTime.now())){
            data.type = "月卡会员";
            data.vipExpireTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(vip.getExpireTime());
        }else if(vip.getType().equals("1") && vip.getExpireTime().isAfter(LocalDateTime.now())){
            data.type = "季卡会员";
            data.vipExpireTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(vip.getExpireTime());
        }else if(vip.getType().equals("2") && vip.getExpireTime().isAfter(LocalDateTime.now())){
            data.type = "年卡会员";
            data.vipExpireTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(vip.getExpireTime());
        }else{
            data.type = "普通用户";
        }
        Banned banned = bannedMapper.findByUserId(user.getId());
        data.bannedCount = banned == null ? 0 : banned.getCount();
        WatchLinkman watchLinkman = watchLinkmanMapper.findByUserId(user.getId());
        data.watchCount = watchLinkman == null ? 0 : watchLinkman.getWatchCount();
        SignIn signIn = signInMapper.findByUserId(user.getId());
        if(null != signIn){
            //获取当月第一天0时的时间
            LocalDateTime mouthFirstTime = LocalDateTime.of(LocalDate.from(LocalDateTime.now().withDayOfMonth(1)), LocalTime.MIN);
            if(signIn.getUpdateTime().isBefore(mouthFirstTime)){
                data.monthCount = 0;
            }else{
                data.monthCount = signIn.getMonthCount();
            }
            data.todaySignIn = signIn.getUpdateTime().isBefore(LocalDateTime.of(LocalDate.from(LocalDateTime.now()), LocalTime.MIN)) ? false : true;
        }else{
            data.monthCount = 0;
            data.todaySignIn = false;
        }
        result.code = "200";
        result.msg = "查询成功！";
        result.data = data;
        return result;
    }

    @Override
    public ModelData logout(HttpServletRequest httpRequest) {
        ModelData result = new ModelData();
        try{
            shiroService.logout(httpRequest.getHeader("token"));
        }catch(Exception e){
            result.code = "500";
            result.msg = "服务器繁忙，请稍后重试！";
            return result;
        }
        result.code = "200";
        result.msg = "登出成功！";
        return result;
    }

}
