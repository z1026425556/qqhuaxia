package com.pengcheng.dao;

import com.pengcheng.domain.Mount;
import org.springframework.stereotype.Repository;

@Repository
public interface MountMapper {

    void addOne(Mount mount);

}
