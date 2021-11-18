package com.pengcheng.dao.sell;

import com.pengcheng.domain.sell.SellOrderImg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellOrderImgMapper {

    void addBatch(List<SellOrderImg> sellOrderImgs);

}
