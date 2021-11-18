package com.pengcheng.dao;

import com.pengcheng.domain.GameNumber;
import org.springframework.stereotype.Repository;

@Repository
public interface GameNumberMapper {

    void addOne(GameNumber gameNumber);

}
