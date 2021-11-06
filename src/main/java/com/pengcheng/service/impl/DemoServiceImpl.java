package com.pengcheng.service.impl;

import com.pengcheng.dao.DemoMapper;
import com.pengcheng.domain.Demo;
import com.pengcheng.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public Demo getDemoById(Long id) {
        return demoMapper.getDemoById(id);
    }
}
