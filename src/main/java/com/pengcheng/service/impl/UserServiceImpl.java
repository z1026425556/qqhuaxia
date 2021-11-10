package com.pengcheng.service.impl;

import com.pengcheng.dao.InviteCodeMapper;
import com.pengcheng.dao.auth.UserMapper;
import com.pengcheng.domain.InviteCode;
import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.auth.User;
import com.pengcheng.service.IShiroService;
import com.pengcheng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private InviteCodeMapper inviteCodeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IShiroService shiroService;

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

}
