package com.pengcheng.controller;

import com.pengcheng.domain.InviteCode;
import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.auth.User;
import com.pengcheng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

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

}















































