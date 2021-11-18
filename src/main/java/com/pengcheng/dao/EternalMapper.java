package com.pengcheng.dao;

import com.pengcheng.domain.Eternal;
import org.springframework.stereotype.Repository;

@Repository
public interface EternalMapper {

    void addOne(Eternal eternal);

}
