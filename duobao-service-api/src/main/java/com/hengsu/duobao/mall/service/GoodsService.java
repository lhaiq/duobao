
package com.hengsu.duobao.mall.service;

import com.hengsu.duobao.mall.model.GoodsModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface GoodsService {

    //商品状态
    public static final int GOODS_STATUS_UNSHELVE = 0;
    public static final int GOODS_STATUS_SHELVE = 1;
    public static final int GOODS_STATUS_PREUNSHELVE = 2;

    //商品类型
    public static final int GOODS_TYPE_COMMON = 0;
    public static final int GOODS_TYPE_10 = 1;
    public static final int GOODS_TYPE_50 = 2;
    public static final int GOODS_TYPE_SUPER = 3;


    public int create(GoodsModel goodsModel);

    public int createSelective(GoodsModel goodsModel);

    public GoodsModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(GoodsModel goodsModel);

    public int updateByPrimaryKeySelective(GoodsModel goodsModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(GoodsModel goodsModel);

    public List<GoodsModel> selectPage(GoodsModel goodsModel, Pageable pageable);

    public void unShelve(Long id);

    public void shelve(Long id);

    public void startNewShopping(Long id);
}