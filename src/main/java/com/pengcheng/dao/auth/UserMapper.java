package com.pengcheng.dao.auth;

import com.pengcheng.domain.auth.Role;
import com.pengcheng.domain.auth.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByUsername(@Param("username") String username);

    User findUserById(@Param("id") Long id);

    void addOne(User user);

    void updateOne(User user);

    Role findRoleByUserId(@Param("id") Long id);

}
