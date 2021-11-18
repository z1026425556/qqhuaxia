package com.pengcheng.dao;

import com.pengcheng.domain.Spirit;
import org.springframework.stereotype.Repository;

@Repository
public interface SpiritMapper {

    void addOne(Spirit spirit);

}
