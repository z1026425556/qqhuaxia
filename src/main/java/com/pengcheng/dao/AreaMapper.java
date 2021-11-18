package com.pengcheng.dao;

import com.pengcheng.domain.Area;
import com.pengcheng.domain.AreaSon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaMapper {

    List<Area> list();

    List<AreaSon> listSonByAreaId(@Param("areaId") Long areaId);

}
