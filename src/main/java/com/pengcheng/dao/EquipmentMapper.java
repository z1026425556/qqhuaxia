package com.pengcheng.dao;

import com.pengcheng.domain.Equipment;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentMapper {

    void addOne(Equipment equipment);

}
