package com.pengcheng.dao.sell;

import com.pengcheng.domain.sell.SellOrderVideo;
import org.springframework.stereotype.Repository;

@Repository
public interface SellOrderVideoMapper {

    void addOne(SellOrderVideo sellOrderVideo);

}
