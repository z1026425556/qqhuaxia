package com.pengcheng.service.impl;

import com.pengcheng.dao.AreaMapper;
import com.pengcheng.domain.ModelData;
import com.pengcheng.service.ISellOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellOrderServiceImpl implements ISellOrderService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public ModelData listArea() {
        ModelData result = new ModelData();
        result.setCode("200");
        result.setMsg("查询成功！");
        result.data = areaMapper.list();
        return result;
    }


}
