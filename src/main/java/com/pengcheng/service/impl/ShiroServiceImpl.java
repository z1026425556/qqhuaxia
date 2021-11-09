package com.pengcheng.service.impl;

import com.pengcheng.dao.auth.SysTokenMapper;
import com.pengcheng.dao.auth.UserMapper;
import com.pengcheng.domain.auth.SysToken;
import com.pengcheng.domain.auth.User;
import com.pengcheng.service.IShiroService;
import com.pengcheng.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShiroServiceImpl implements IShiroService {

    //有效时间
    private final static int EXPIRE = 12;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysTokenMapper sysTokenMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public Map<String, Object> createToken(Long userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        //生成一个token
        String token = TokenGenerator.generateValue();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //判断原来是否有token
        SysToken sysToken = sysTokenMapper.findByUserId(userId);
        if(sysToken == null){
            sysToken = new SysToken();
            sysToken.setUserId(userId);
            sysToken.setToken(token);
            sysToken.setUpdateTime(now);
            sysToken.setExpireTime(expireTime);
            sysTokenMapper.addOne(sysToken);
        }else{
            sysToken.setToken(token);
            sysToken.setUpdateTime(now);
            sysToken.setExpireTime(expireTime);
            sysTokenMapper.updateOne(sysToken);
        }
        result.put("token", token);
        result.put("expire", expireTime);
        return result;
    }

    @Override
    public void logout(String token) {
        SysToken sysToken = sysTokenMapper.findByToken(token);
        token = TokenGenerator.generateValue();
        sysToken.setToken(token);
        sysTokenMapper.updateOne(sysToken);
    }

    @Override
    public SysToken findByToken(String accessToken) {
        return sysTokenMapper.findByToken(accessToken);
    }

    @Override
    public User findByUserId(Long userId) {
        return userMapper.findUserById(userId);
    }


}












































