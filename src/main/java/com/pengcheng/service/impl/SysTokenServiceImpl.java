package com.pengcheng.service.impl;

import com.pengcheng.dao.auth.SysTokenMapper;
import com.pengcheng.domain.auth.SysToken;
import com.pengcheng.service.ISysTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysTokenServiceImpl implements ISysTokenService {

    @Autowired
    private SysTokenMapper sysTokenMapper;

    @Override
    public void saveSysToken(SysToken sysToken) {

        if(sysToken.getId() == null){
            sysTokenMapper.updateOne(sysToken);
        }else{
            sysTokenMapper.addOne(sysToken);
        }

    }

}
