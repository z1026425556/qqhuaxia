package com.pengcheng.dao;

import com.pengcheng.domain.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaMapper {

    List<Area> list();

}
