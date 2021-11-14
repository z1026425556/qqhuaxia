package com.pengcheng.controller;

import com.pengcheng.domain.ModelData;
import com.pengcheng.service.ISellOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sellOrder")
public class SellOrderController {

    @Autowired
    private ISellOrderService sellOrderService;

    @ResponseBody
    @RequestMapping("/listArea")
    public ModelData listArea(){
        return sellOrderService.listArea();
    }

}
