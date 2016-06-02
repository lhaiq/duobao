package com.hengsu.duobao.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.hengsu.duobao.mall.model.BuyShoppingModel;
import com.hengsu.duobao.mall.model.ShoppingModel;
import com.hengsu.duobao.mall.service.CodeService;
import com.hengsu.duobao.mall.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.duobao.mall.entity.Order;
import com.hengsu.duobao.mall.repository.OrderRepository;
import com.hengsu.duobao.mall.model.OrderModel;
import com.hengsu.duobao.mall.service.OrderService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

import static com.hengsu.duobao.ErrorCode.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CodeService codeService;

    @Autowired
    private ShoppingService shoppingService;

    @Transactional
    @Override
    public int create(OrderModel orderModel) {
        return orderRepo.insert(beanMapper.map(orderModel, Order.class));
    }

    @Transactional
    @Override
    public int createSelective(OrderModel orderModel) {
        return orderRepo.insertSelective(beanMapper.map(orderModel, Order.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderModel findByPrimaryKey(Long id) {
        Order order = orderRepo.selectByPrimaryKey(id);
        return beanMapper.map(order, OrderModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(OrderModel orderModel) {
        return orderRepo.selectCount(beanMapper.map(orderModel, Order.class));
    }

    @Override
    public OrderModel preApply(String orderId, double money) {

        String sql = "select * from mall_order where order_id = ?";
        OrderModel orderModel = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(OrderModel.class), orderId);

        //检查状态
        if (orderModel.getStatus() != ORDER_STATUS_CREATE) {
            throwBusinessException(ORDER_STATUS_INVALID);
        }

        //检查钱
        if (orderModel.getMoney() != money) {
            throwBusinessException(MONEY_NOT_ENOUGH);
        }

        return orderModel;
    }

    @Override
    public void postApply(OrderModel orderModel) {

        //更新订单状态
        OrderModel param = new OrderModel();
        param.setId(orderModel.getId());
        param.setStatus(ORDER_STATUS_FINISH);
        param.setFinishTime(System.currentTimeMillis());
        updateByPrimaryKeySelective(param);

        List<BuyShoppingModel> buyShoppingModels = JSON.parseArray(orderModel.getShopList(),
                BuyShoppingModel.class);
        for (BuyShoppingModel buyShoppingModel : buyShoppingModels) {
            //生成code
            codeService.allotCode(orderModel.getUserId(),orderModel.getId(), buyShoppingModel);

            //判断是否有结束的
            shoppingService.checkIsFinished(buyShoppingModel.getShopId());
        }

    }

    @Override
    @Transactional
    public void applyByVirtual(String orderId, Double money) {
        OrderModel orderModel = preApply(orderId, money);
        //TODO 检查虚拟币
        postApply(orderModel);

    }

    @Override
    @Transactional
    public void cancelOrderTimeOut(List<OrderModel> orderModels) {
        //将超时订单返回
        String sql = "select * from mall_shop where id = ? for update";
        for (OrderModel orderModel : orderModels) {
            List<BuyShoppingModel> buyShoppingModels = JSON.parseArray(orderModel.getShopList(),
                    BuyShoppingModel.class);
            for (BuyShoppingModel buyShoppingModel : buyShoppingModels) {
                ShoppingModel shoppingModel = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ShoppingModel.class),
                        buyShoppingModel.getShopId());
                ShoppingModel param = new ShoppingModel();
                param.setId(shoppingModel.getId());
                param.setRemainNum(shoppingModel.getRemainNum() + buyShoppingModel.getNum());
                shoppingService.updateByPrimaryKeySelective(param);
            }


        }
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(OrderModel orderModel) {
        return orderRepo.updateByPrimaryKey(beanMapper.map(orderModel, Order.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(OrderModel orderModel) {
        return orderRepo.updateByPrimaryKeySelective(beanMapper.map(orderModel, Order.class));
    }


}
