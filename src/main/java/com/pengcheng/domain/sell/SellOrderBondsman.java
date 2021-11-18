package com.pengcheng.domain.sell;

public class SellOrderBondsman {

    private Long id;
    private Long sellOrderId;
    private Long bondsmanId;

    public Long getBondsmanId() {
        return bondsmanId;
    }

    public void setBondsmanId(Long bondsmanId) {
        this.bondsmanId = bondsmanId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(Long sellOrderId) {
        this.sellOrderId = sellOrderId;
    }
}
