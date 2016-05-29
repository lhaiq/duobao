package com.hengsu.duobao.mall.model;

/**
 * Created by haiquanli on 16/5/19.
 */
public class BuyShoppingModel {

    private Long shopId;

    private Integer num;

    private Integer times;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getTimes() {
        return times;
    }
}

