package com.pengcheng.service;


import com.pengcheng.domain.auth.SysToken;
import com.pengcheng.domain.auth.User;

import java.util.Map;

public interface IShiroService {

    User findByUsername(String username);

    Map<String, Object> createToken(Long userId);

    void logout(String token);

    SysToken findByToken(String accessToken);

    User findByUserId(Long userId);

    User findByAccessToken(String accessToken);

}
