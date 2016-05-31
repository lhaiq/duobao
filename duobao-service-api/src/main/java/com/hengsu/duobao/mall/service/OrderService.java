
package com.hengsu.duobao.mall.service;

import com.hengsu.duobao.mall.model.OrderModel;

import java.util.Date;
import java.util.List;

public interface OrderService {

    public static final int ORDER_STATUS_CREATE=0;
    public static final int ORDER_STATUS_FINISH=1;
    public static final int ORDER_STATUS_FAILURE=2;


    public int create(OrderModel orderModel);

    public int createSelective(OrderModel orderModel);

    public OrderModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(OrderModel orderModel);

    public int updateByPrimaryKeySelective(OrderModel orderModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(OrderModel orderModel);

    public OrderModel preApply(String orderId, double money);

    public void postApply(OrderModel orderModel);

    public void applyByVirtual(String orderId, Double money);

    public void cancelOrderTimeOut(List<OrderModel> orderModels);

}