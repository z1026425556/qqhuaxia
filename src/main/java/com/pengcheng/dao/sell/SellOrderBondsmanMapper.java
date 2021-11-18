package com.pengcheng.dao.sell;

import com.pengcheng.domain.sell.SellOrderBondsman;
import org.springframework.stereotype.Repository;

@Repository
public interface SellOrderBondsmanMapper {

    void addOne(SellOrderBondsman sellOrderBondsman);

}
