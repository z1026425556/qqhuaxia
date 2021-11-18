package com.pengcheng.dao;

import com.pengcheng.domain.Prop;
import org.springframework.stereotype.Repository;

@Repository
public interface PropMapper {

    void addOne(Prop prop);

}
