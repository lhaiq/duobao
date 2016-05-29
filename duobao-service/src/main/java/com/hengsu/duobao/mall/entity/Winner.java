package com.hengsu.duobao.mall.entity;

import java.util.Date;

public class Winner {
    private Long id;

    private Long shopId;

    private Long userId;

    private Long code;

    private Date datetime;

    private Long referCode;

    private Long timeCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Long getReferCode() {
        return referCode;
    }

    public void setReferCode(Long referCode) {
        this.referCode = referCode;
    }

    public Long getTimeCode() {
        return timeCode;
    }

    public void setTimeCode(Long timeCode) {
        this.timeCode = timeCode;
    }
}