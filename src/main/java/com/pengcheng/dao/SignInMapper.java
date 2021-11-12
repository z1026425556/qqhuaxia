package com.pengcheng.dao;

import com.pengcheng.domain.SignIn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SignInMapper {

    void addOne(SignIn signIn);

    void updateOne(SignIn signIn);

    SignIn findByUserId(@Param("userId") Long userId);

}
