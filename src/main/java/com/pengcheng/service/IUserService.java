package com.pengcheng.service;

import com.pengcheng.domain.InviteCode;
import com.pengcheng.domain.ModelData;
import com.pengcheng.domain.auth.User;
import org.apache.ibatis.annotations.Param;

public interface IUserService {

    InviteCode findByCode(String inviteCode);

    User findByUsername(String username);

    ModelData register(User user, String inviteCode);

}
