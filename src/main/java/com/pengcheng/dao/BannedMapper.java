package com.pengcheng.dao;

import com.pengcheng.domain.Banned;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BannedMapper {

    Banned findByUserId(@Param("userId") Long userId);

}
