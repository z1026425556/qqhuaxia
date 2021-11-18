package com.pengcheng.service;

import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.sell.IssuaSellParam;

import javax.servlet.http.HttpServletRequest;

public interface ISellOrderService {

    ModelData listArea();

    ModelData listSonByAreaId(Long areaId);

    ModelData listBondsman();

    ModelData issuaSell(HttpServletRequest httpRequest, IssuaSellParam issuaSellParam);

}
