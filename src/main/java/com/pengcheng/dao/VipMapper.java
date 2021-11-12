package com.pengcheng.dao;

import com.pengcheng.domain.Vip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VipMapper {

    Vip findByUserId(@Param("userId") Long userId);

}
