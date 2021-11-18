package com.pengcheng.dao.sell;

import com.pengcheng.domain.sell.UserSellOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSellOrderMapper {

    void addOne(UserSellOrder userSellOrder);

}
