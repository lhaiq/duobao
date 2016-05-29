package com.hengsu.duobao.mall.controller;

import com.hengsu.duobao.core.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.duobao.mall.service.GoodsService;
import com.hengsu.duobao.mall.model.GoodsModel;
import com.hengsu.duobao.mall.vo.GoodsVO;

import java.util.Date;
import java.util.List;

@RestApiController
@RequestMapping("/duobao")
public class GoodsRestApiController {

    private final Logger logger = LoggerFactory.getLogger(GoodsRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private GoodsService goodsService;


    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mall/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<GoodsVO>> getGoodsById(@PathVariable Long id) {
        GoodsModel goodsModel = goodsService.findByPrimaryKey(id);
        GoodsVO goodsVO = beanMapper.map(goodsModel, GoodsVO.class);
        ResponseEnvelope<GoodsVO> responseEnv = new ResponseEnvelope<>(goodsVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 商品列表
     *
     * @param categoryId
     * @param pageable
     * @param sellerId
     * @param type
     * @return
     */
    @RequestMapping(value = "/mall/{categoryId}/goods", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<GoodsModel>>> listGoods(@PathVariable Long categoryId,
                                                                        @RequestParam(required = false) Long sellerId,
                                                                        @RequestParam(required = false) Integer type,
                                                                        Pageable pageable) {
        GoodsModel param = new GoodsModel();
        param.setType(type);
        param.setSellerId(sellerId);
        param.setCategoryId(categoryId);
        List<GoodsModel> goodsModel = goodsService.selectPage(param, pageable);
        int count = goodsService.selectCount(param);
        Page<GoodsModel> page = new PageImpl<>(goodsModel, pageable, count);
        ResponseEnvelope<Page<GoodsModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 添加商品
     *
     * @param goodsVO
     * @return
     */
    @RequestMapping(value = "/mall/goods", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createGoods(@RequestBody GoodsVO goodsVO) {
        GoodsModel goodsModel = beanMapper.map(goodsVO, GoodsModel.class);
        goodsModel.setAddTime(new Date());
        Integer result = goodsService.createSelective(goodsModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mall/goods/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteGoodsByPrimaryKey(@PathVariable Long id) {
        Integer result = goodsService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 修改商品
     *
     * @param id
     * @param goodsVO
     * @return
     */
    @RequestMapping(value = "/mall/goods/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateGoodsByPrimaryKeySelective(@PathVariable Long id,
                                                                                      @RequestBody GoodsVO goodsVO) {
        GoodsModel goodsModel = beanMapper.map(goodsVO, GoodsModel.class);
        goodsModel.setId(id);
        //TODO 判断状态
        Integer result = goodsService.updateByPrimaryKeySelective(goodsModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 上架
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mall/shelve/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> shelve(@PathVariable Long id) {
        goodsService.shelve(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 下架
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mall/unShelve/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> unShelve(@PathVariable Long id) {
        goodsService.unShelve(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
