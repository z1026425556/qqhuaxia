package com.pengcheng.dao.sell;

import com.pengcheng.domain.sell.SellOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface SellOrderMapper {

    void addOne(SellOrder sellOrder);

}
