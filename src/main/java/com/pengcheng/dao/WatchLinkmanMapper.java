package com.pengcheng.dao;

import com.pengcheng.domain.WatchLinkman;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchLinkmanMapper {

    void addOne(WatchLinkman watchLinkman);

    void updateOne(WatchLinkman watchLinkman);

    WatchLinkman findByUserId(@Param("userId") Long userId);

}
