package com.pengcheng.controller;

import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.sell.IssuaSellParam;
import com.pengcheng.service.ISellOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


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

    @ResponseBody
    @RequestMapping("/listSonByAreaId")
    public ModelData listSonByAreaId(Long areaId){
        return sellOrderService.listSonByAreaId(areaId);
    }

    @ResponseBody
    @RequestMapping("/listBondsman")
    public ModelData listBondsman() {
        return sellOrderService.listBondsman();
    }

    @ResponseBody
    @RequestMapping("/issuaSell")
    public ModelData issuaSell(HttpServletRequest httpRequest, IssuaSellParam issuaSellParam) {
        return sellOrderService.issuaSell(httpRequest, issuaSellParam);
    }

}
