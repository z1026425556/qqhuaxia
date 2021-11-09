package com.pengcheng.dao.auth;

import com.pengcheng.domain.auth.SysToken;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysTokenMapper {

    SysToken findByToken(@Param("token") String token);

    SysToken findByUserId(@Param("userId") Long userId);

    void addOne(SysToken sysToken);

    void updateOne(SysToken sysToken);

}
