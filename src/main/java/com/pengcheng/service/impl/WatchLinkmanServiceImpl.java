package com.pengcheng.service.impl;

import com.pengcheng.dao.WatchLinkmanMapper;
import com.pengcheng.domain.WatchLinkman;
import com.pengcheng.service.IWatchLinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchLinkmanServiceImpl implements IWatchLinkmanService {

    @Autowired
    private WatchLinkmanMapper watchLinkmanMapper;

    @Override
    public void save(WatchLinkman watchLinkman) {
        if(watchLinkman.getId() == null){
            watchLinkmanMapper.addOne(watchLinkman);
        } else {
            watchLinkmanMapper.updateOne(watchLinkman);
        }
    }


}
