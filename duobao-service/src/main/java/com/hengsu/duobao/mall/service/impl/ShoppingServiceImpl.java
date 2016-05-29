package com.hengsu.duobao.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.hengsu.duobao.core.BillNumberBuilder;
import com.hengsu.duobao.mall.model.BuyShoppingModel;
import com.hengsu.duobao.mall.model.OrderModel;
import com.hengsu.duobao.mall.service.GoodsService;
import com.hengsu.duobao.mall.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.duobao.mall.entity.Shopping;
import com.hengsu.duobao.mall.repository.ShoppingRepository;
import com.hengsu.duobao.mall.model.ShoppingModel;
import com.hengsu.duobao.mall.service.ShoppingService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.hengsu.duobao.ErrorCode.*;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ShoppingRepository shoppingRepo;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private WinnerService winnerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public int create(ShoppingModel shoppingModel) {
        return shoppingRepo.insert(beanMapper.map(shoppingModel, Shopping.class));
    }

    @Transactional
    @Override
    public int createSelective(ShoppingModel shoppingModel) {
        return shoppingRepo.insertSelective(beanMapper.map(shoppingModel, Shopping.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return shoppingRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ShoppingModel findByPrimaryKey(Long id) {
        Shopping shopping = shoppingRepo.selectByPrimaryKey(id);
        return beanMapper.map(shopping, ShoppingModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(ShoppingModel shoppingModel) {
        return shoppingRepo.selectCount(beanMapper.map(shoppingModel, Shopping.class));
    }

    @Override
    public List<ShoppingModel> selectPage(ShoppingModel shoppingModel, Pageable pageable) {
        List<Shopping> shoppings = shoppingRepo.selectPage(beanMapper.map(shoppingModel, Shopping.class), pageable);
        return beanMapper.mapAsList(shoppings, ShoppingModel.class);
    }

    @Transactional
    @Override
    public String buyShoppings(Long userId, List<BuyShoppingModel> buyShoppingModels) {

        String sql = "select g.type from mall_shopping s,mall_goods g\n" +
                "where s.goods_id = g.id\n" +
                "and s.id=?";

        int money = 0;

        for (BuyShoppingModel buyShoppingModel : buyShoppingModels) {
            Integer type = jdbcTemplate.queryForInt(sql, buyShoppingModel.getShopId());
            buyShopping(buyShoppingModel.getShopId(), type, buyShoppingModel.getNum());
            money += buyShoppingModel.getNum();
        }

        //创建订单
        String orderId = BillNumberBuilder.nextBillNumber();
        OrderModel orderModel = new OrderModel();
        orderModel.setApplyTime(new Date());
        orderModel.setUserId(userId);
        orderModel.setMoney(money * 1.0);
        orderModel.setShopList(JSON.toJSONString(buyShoppingModels));
        orderModel.setOrderId(orderId);
        return orderId;

    }

    @Override
    @Transactional
    public void checkIsFinished(Long shopId) {

        //检查是否卖完
        ShoppingModel shoppingModel = findByPrimaryKey(shopId);
        if (shoppingModel.getRemainNum() != 0) return;

        //如果卖完,更新状态
        ShoppingModel param = new ShoppingModel();
        param.setId(shopId);
        param.setStatus(STATUS_FINISHED);

        //开始新的一期
        goodsService.startNewShopping(shoppingModel.getGoodsId());

        //抽奖
        winnerService.lottery(shopId);

    }

    @Override
    public Page<Map<String, Object>> searchHot(Integer cityCode, Pageable pageable) {
        String sql = "select s.*,g.name,g.image,g.price from mall_shopping s,mall_goods g " +
                "where s.goods_id=g.id " +
                " and s.status=0 order by s.serial_number desc limit ?,?";
        String countSql = "select count(1) from  mall_shopping where status=0";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, pageable.getPageNumber(), pageable.getPageSize());
        long count = jdbcTemplate.queryForLong(countSql);
        Page<Map<String, Object>> page = new PageImpl<>(results, pageable, count);
        return page;
    }

    @Override
    public Page<Map<String, Object>> searchFast(Integer cityCode, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> searchNew(Integer cityCode, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> searchHigh(Integer cityCode, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> searchLow(Integer cityCode, Pageable pageable) {
        return null;
    }

    private void buyShopping(Long shopId, int type, int num) {

        String sql = "select * from mall_shopping where id = ? for update";
        ShoppingModel shoppingModel = jdbcTemplate.queryForObject(sql, ShoppingModel.class, shopId);

        //判断状态
        if (ShoppingService.STATUS_FINISHED == shoppingModel.getStatus()) {
            throwBusinessException(SHOP_FINISHED);
        }

        //判断余额
        if (shoppingModel.getRemainNum() < num) {
            throwBusinessException(GOODS_NOT_ENOUGH);
        }

        //根据类型,判断num是否合法,以及虚拟币是否足够
        if (GoodsService.GOODS_TYPE_10 == type) {
            if (num / (shoppingModel.getNum() * 0.1) != 0) {
                throwBusinessException(NUM_INVALID);
            }

        } else if (GoodsService.GOODS_TYPE_50 == type) {
            if (shoppingModel.getNum() * 0.5 % num != 0) {
                throwBusinessException(NUM_INVALID);
            }

        }

        //锁定库存
        ShoppingModel param = new ShoppingModel();
        param.setId(shopId);
        param.setRemainNum(shoppingModel.getRemainNum() - num);
        updateByPrimaryKeySelective(param);
    }


    @Transactional
    @Override
    public int updateByPrimaryKey(ShoppingModel shoppingModel) {
        return shoppingRepo.updateByPrimaryKey(beanMapper.map(shoppingModel, Shopping.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ShoppingModel shoppingModel) {
        return shoppingRepo.updateByPrimaryKeySelective(beanMapper.map(shoppingModel, Shopping.class));
    }

}
