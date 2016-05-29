package com.hengsu.duobao.mall.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by haiquanli on 16/5/19.
 */
public class BuyShoppingVO {

    @NotEmpty
    private Long shopId;

    @NotEmpty
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
