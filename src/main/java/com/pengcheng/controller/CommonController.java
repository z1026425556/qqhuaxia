package com.pengcheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/checkToken")
    @ResponseBody
    public String checkToken(){
        return "200";
    }

}
