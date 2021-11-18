package com.pengcheng.dao;

import com.pengcheng.domain.WordMoney;
import org.springframework.stereotype.Repository;

@Repository
public interface WordMoneyMapper {

    void addOne(WordMoney wordMoney);

}
