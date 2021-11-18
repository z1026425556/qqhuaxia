package com.pengcheng.dao;

import com.pengcheng.domain.Bondsman;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BondsmanMapper {

    List<Bondsman> listAll();

}
