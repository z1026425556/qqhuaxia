package com.pengcheng.controller;

import com.pengcheng.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @RequestMapping("/getUser/{id}")
    public String getUser(@PathVariable Long id){
        return demoService.getDemoById(id).toString();
    }

}
