package com.pengcheng.dao;

import com.pengcheng.domain.Ingot;
import org.springframework.stereotype.Repository;

@Repository
public interface IngotMapper {

    void addOne(Ingot ingot);

}
