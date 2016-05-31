package com.hengsu.duobao.mall.controller;

import com.hengsu.duobao.core.ReturnCode;
import com.hengsu.duobao.mall.model.WinnerModel;
import com.hengsu.duobao.mall.service.OrderService;
import com.hengsu.duobao.mall.service.WinnerService;
import com.hengsu.duobao.mall.vo.ApplyVirtualVO;
import com.hengsu.duobao.mall.vo.WinnerVO;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestApiController
@RequestMapping("/duobao")
public class OrderRestApiController {

	private final Logger logger = LoggerFactory.getLogger(OrderRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/mall/order/virtual", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<String>> applyByVirtual(@RequestBody ApplyVirtualVO applyVirtualVO){
		orderService.applyByVirtual(applyVirtualVO.getOrderId(),applyVirtualVO.getMoney());
		ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	

	
}
