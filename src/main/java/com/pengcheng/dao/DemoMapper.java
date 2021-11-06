package com.pengcheng.dao;

import com.pengcheng.domain.Demo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoMapper {

    Demo getDemoById(@Param("id") Long id);

}
