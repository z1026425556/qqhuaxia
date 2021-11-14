package com.pengcheng.dao;

import com.pengcheng.domain.Pay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMapper {

    void addOne(Pay pay);

    void deleteById(@Param("id") Long id);

    Pay findByUserId(@Param("userId") Long userId);

    void addLog(Pay pay);

}
