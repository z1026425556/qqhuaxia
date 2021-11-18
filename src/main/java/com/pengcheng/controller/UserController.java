package com.pengcheng.controller;

import com.pengcheng.domain.InviteCode;
import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.auth.User;
import com.pengcheng.service.ISigninService;
import com.pengcheng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISigninService signinService;

    @ResponseBody
    @RequestMapping("/isExistUser")
    public Boolean isExistUser(String username){
        User user = userService.findByUsername(username);
        if(user == null){
            return false;
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/isHavingInviteCode")
    public Boolean isHavingInviteCode(String inputInviteCode){
        InviteCode code = userService.findByCode(inputInviteCode);
        if(code == null){
            return false;
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/register")
    public ModelData register(User user, String inviteCode){
        return userService.register(user, inviteCode);
    }

    @ResponseBody
    @RequestMapping("/login")
    public ModelData login(User inputUser){
        return userService.login(inputUser);
    }

    @ResponseBody
    @RequestMapping("/resetPwd")
    public ModelData resetPwd(User inputUser, String resetPwd){
        return userService.resetPwd(inputUser, resetPwd);
    }

    @ResponseBody
    @RequestMapping("/queryPersonalInfo")
    public ModelData queryPersonalInfo(HttpServletRequest httpRequest){
        return userService.queryPersonalInfo(httpRequest);
    }

    @ResponseBody
    @RequestMapping("/sign")
    public ModelData sign(HttpServletRequest httpRequest) {
        return signinService.sign(httpRequest);
    }

    @ResponseBody
    @RequestMapping("/logout")
    public ModelData logout(HttpServletRequest httpRequest){
        return userService.logout(httpRequest);
    }

    @ResponseBody
    @RequestMapping("/userInfo")
    public User userInfo(HttpServletRequest httpRequest){
        return userService.findByAccessToken(httpRequest);
    }

}















































