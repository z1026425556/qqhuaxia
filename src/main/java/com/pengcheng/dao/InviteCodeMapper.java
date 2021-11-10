package com.pengcheng.dao;

import com.pengcheng.domain.InviteCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteCodeMapper {

    InviteCode findByCode(@Param("inviteCode") String inviteCode);

    void deleteByCode(@Param("inviteCode") String inviteCode);

}
