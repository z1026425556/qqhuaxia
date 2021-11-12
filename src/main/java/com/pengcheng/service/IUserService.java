package com.pengcheng.service;

import com.pengcheng.domain.InviteCode;
import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.auth.User;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {

    InviteCode findByCode(String inviteCode);

    User findByUsername(String username);

    ModelData register(User user, String inviteCode);

    ModelData login(User inputUser);

    ModelData resetPwd(User inputUser, String resetPwd);

    ModelData queryPersonalInfo(HttpServletRequest httpRequest);

}
