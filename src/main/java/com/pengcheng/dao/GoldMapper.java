package com.pengcheng.dao;

import com.pengcheng.domain.Gold;
import org.springframework.stereotype.Repository;

@Repository
public interface GoldMapper {


    void addOne(Gold gold);


}
