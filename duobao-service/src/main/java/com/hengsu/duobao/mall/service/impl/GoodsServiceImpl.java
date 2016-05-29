package com.hengsu.duobao.mall.service.impl;

import com.hengsu.duobao.mall.model.ShoppingModel;
import com.hengsu.duobao.mall.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.duobao.mall.entity.Goods;
import com.hengsu.duobao.mall.repository.GoodsRepository;
import com.hengsu.duobao.mall.model.GoodsModel;
import com.hengsu.duobao.mall.service.GoodsService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import static com.hengsu.duobao.ErrorCode.*;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private GoodsRepository goodsRepo;

	@Autowired
	private ShoppingService shoppingService;

	@Transactional
	@Override
	public int create(GoodsModel goodsModel) {
		return goodsRepo.insert(beanMapper.map(goodsModel, Goods.class));
	}

	@Transactional
	@Override
	public int createSelective(GoodsModel goodsModel) {
		return goodsRepo.insertSelective(beanMapper.map(goodsModel, Goods.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return goodsRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public GoodsModel findByPrimaryKey(Long id) {
		Goods goods = goodsRepo.selectByPrimaryKey(id);
		return beanMapper.map(goods, GoodsModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(GoodsModel goodsModel) {
		return goodsRepo.selectCount(beanMapper.map(goodsModel, Goods.class));
	}

	@Override
	public List<GoodsModel> selectPage(GoodsModel goodsModel, Pageable pageable) {
		List<Goods> goodses = goodsRepo.selectPage(beanMapper.map(goodsModel,Goods.class),pageable);
		return beanMapper.mapAsList(goodses,GoodsModel.class);
	}

	@Transactional
	@Override
	public void unShelve(Long id) {

		//先查是否有正在抢购的商品,如有则是预下架,没有则下架
		ShoppingModel param = new ShoppingModel();
		param.setGoodsId(id);
		param.setStatus(ShoppingService.STATUS_UNFINISHED);
		int count = shoppingService.selectCount(param);
		GoodsModel goodsModel = new GoodsModel();
		goodsModel.setId(id);
		if(count>0){
			goodsModel.setStatus(GoodsService.GOODS_STATUS_PREUNSHELVE);
		}else{
			goodsModel.setStatus(GoodsService.GOODS_STATUS_UNSHELVE);
		}

		updateByPrimaryKeySelective(goodsModel);
	}

	@Transactional
	@Override
	public void shelve(Long id) {

		//只有下架的商品才能上架
		GoodsModel goodsModel = findByPrimaryKey(id);
		if(GOODS_STATUS_UNSHELVE!=goodsModel.getStatus()){
			throwBusinessException(CANNOT_SHELVE);
		}

		GoodsModel param = new GoodsModel();
		param.setId(id);
		param.setStatus(GoodsService.GOODS_STATUS_SHELVE);
		updateByPrimaryKeySelective(param);

		//开始新的一期
		startNewShopping(id);
	}

	@Transactional
	@Override
	public void startNewShopping(Long id) {

		GoodsModel goodsModel = findByPrimaryKey(id);

		//如果处于预下架,则下架
		if (GoodsService.GOODS_STATUS_PREUNSHELVE == goodsModel.getStatus()) {
			unShelve(id);
			return;
		}

		//新的shopping
		ShoppingModel shoppingModel = new ShoppingModel();
		shoppingModel.setGoodsId(goodsModel.getId());
		shoppingModel.setSerialNumber(goodsModel.getCurrentSerial()+1);
		shoppingModel.setNum(goodsModel.getNum());
		shoppingModel.setRemainNum(goodsModel.getNum());
		shoppingService.createSelective(shoppingModel);

		//更新商品期数
		GoodsModel param = new GoodsModel();
		param.setId(goodsModel.getId());
		param.setCurrentSerial(goodsModel.getCurrentSerial() + 1);
		updateByPrimaryKeySelective(param);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(GoodsModel goodsModel) {
		return goodsRepo.updateByPrimaryKey(beanMapper.map(goodsModel, Goods.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(GoodsModel goodsModel) {
		return goodsRepo.updateByPrimaryKeySelective(beanMapper.map(goodsModel, Goods.class));
	}

}
