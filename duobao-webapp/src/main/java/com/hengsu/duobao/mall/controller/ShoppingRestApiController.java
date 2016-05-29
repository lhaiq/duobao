package com.hengsu.duobao.mall.controller;

import com.hengsu.duobao.mall.model.BuyShoppingModel;
import com.hengsu.duobao.mall.vo.BuyShoppingVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.duobao.mall.service.ShoppingService;
import com.hengsu.duobao.mall.model.ShoppingModel;
import com.hengsu.duobao.mall.vo.ShoppingVO;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestApiController
@RequestMapping("/duobao")
public class ShoppingRestApiController {

    private static final String HOME_HOT = "hot";
    private static final String HOME_FAST = "fast";
    private static final String HOME_NEW = "new";
    private static final String HOME_HIGH = "high";
    private static final String HOME_LOW = "low";

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ShoppingService shoppingService;


    @RequestMapping(value = "/mall/shoppings/home", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<Map<String, Object>>>> homeShopping(@RequestParam Integer cityCode,
                                                                                    @RequestParam String type,
                                                                                    Pageable pageable) {
        Page<Map<String, Object>> page = null;
        if (HOME_HOT.equals(type)) {
            page = shoppingService.searchHot(cityCode, pageable);
        }
        else if(HOME_FAST.equals(type)){
            page = shoppingService.searchFast(cityCode, pageable);
        }
        else if(HOME_NEW.equals(type)){
            page = shoppingService.searchNew(cityCode, pageable);
        }
        else if(HOME_HIGH.equals(type)){
            page = shoppingService.searchHigh(cityCode, pageable);
        }
        else if(HOME_LOW.equals(type)){
            page = shoppingService.searchLow(cityCode, pageable);
        }

        //TODO

        ResponseEnvelope<Page<Map<String, Object>>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    @RequestMapping(value = "/mall/shoppings", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Map>> listShoppings(@RequestBody List<Long> ids) {
        Map map = new HashMap<>();
        for (Long id : ids) {
            map.put(id, shoppingService.findByPrimaryKey(id));
        }

        ResponseEnvelope<Map> responseEnv = new ResponseEnvelope<>(map, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/mall/buy/shopping", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> buyShopping(@Valid @RequestBody List<BuyShoppingVO> shoppingVOs) {
        List<BuyShoppingModel> buyShoppingModels = beanMapper.mapAsList(shoppingVOs, BuyShoppingModel.class);
        //TODO
        shoppingService.buyShoppings(1L, buyShoppingModels);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(1, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
