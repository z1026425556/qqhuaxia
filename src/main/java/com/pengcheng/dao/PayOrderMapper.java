package com.pengcheng.dao;

import com.pengcheng.domain.PayCallback;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayOrderMapper {

    void insertPayOrder(PayCallback info);

    String querySuccessWX(@Param("zzfOId") String zzfOId);

    String queryWX(@Param("oId") Long oId);

}
