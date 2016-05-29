
package com.hengsu.duobao.mall.service;

import com.hengsu.duobao.mall.model.BuyShoppingModel;
import com.hengsu.duobao.mall.model.ShoppingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ShoppingService {

    public static final int STATUS_UNFINISHED = 0;

    public static final int STATUS_FINISHED = 1;

    public int create(ShoppingModel shoppingModel);

    public int createSelective(ShoppingModel shoppingModel);

    public ShoppingModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(ShoppingModel shoppingModel);

    public int updateByPrimaryKeySelective(ShoppingModel shoppingModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(ShoppingModel shoppingModel);

    public List<ShoppingModel> selectPage(ShoppingModel shoppingModel, Pageable pageable);

    public String buyShoppings(Long userId, List<BuyShoppingModel> buyShoppingModels);

    public void checkIsFinished(Long shopId);

    public Page<Map<String, Object>> searchHot(Integer cityCode, Pageable pageable);

    public Page<Map<String, Object>> searchFast(Integer cityCode, Pageable pageable);

    public Page<Map<String, Object>> searchNew(Integer cityCode, Pageable pageable);

    public Page<Map<String, Object>>searchHigh(Integer cityCode, Pageable pageable);

    public Page<Map<String, Object>> searchLow(Integer cityCode, Pageable pageable);

}