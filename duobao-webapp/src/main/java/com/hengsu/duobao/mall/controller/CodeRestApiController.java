package com.hengsu.duobao.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.duobao.mall.service.CodeService;
import com.hengsu.duobao.mall.model.CodeModel;
import com.hengsu.duobao.mall.vo.CodeVO;

import java.util.List;

@RestApiController
@RequestMapping("/duobao")
public class CodeRestApiController {

	private final Logger logger = LoggerFactory.getLogger(CodeRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private CodeService codeService;
	
	@RequestMapping(value = "/mall/codes", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<List<CodeModel>>> getCodeById(@RequestParam Long shopId,
																@RequestParam Long orderId){

		CodeModel param = new CodeModel();
		param.setShopId(shopId);
		param.setOrderId(orderId);
		List<CodeModel> codeModels = codeService.selectPage(param,new PageRequest(0,Integer.MAX_VALUE));
		ResponseEnvelope<List<CodeModel>> responseEnv = new ResponseEnvelope<List<CodeModel>>(codeModels,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	

	
}
