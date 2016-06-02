package com.hengsu.duobao.core.vo;

/**
 * Created by haiquanli on 16/6/2.
 */
public class TransferVO {

    private Long toId;
    private Long amount;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }
}
