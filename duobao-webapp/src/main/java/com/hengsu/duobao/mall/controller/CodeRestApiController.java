package com.hengsu.duobao.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.duobao.mall.service.CodeService;
import com.hengsu.duobao.mall.model.CodeModel;
import com.hengsu.duobao.mall.vo.CodeVO;

@RestApiController
@RequestMapping("/duobao")
public class CodeRestApiController {

	private final Logger logger = LoggerFactory.getLogger(CodeRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private CodeService codeService;
	
	@RequestMapping(value = "/mall/code/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<CodeVO>> getCodeById(@PathVariable Long id){
		CodeModel codeModel = codeService.findByPrimaryKey(id);
		CodeVO codeVO =beanMapper.map(codeModel, CodeVO.class);
		ResponseEnvelope<CodeVO> responseEnv = new ResponseEnvelope<CodeVO>(codeVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/code", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createCode(@RequestBody CodeVO codeVO){
		CodeModel codeModel = beanMapper.map(codeVO, CodeModel.class);
		Integer  result = codeService.create(codeModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/code/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteCodeByPrimaryKey(@PathVariable Long id){
		Integer  result = codeService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/code/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateCodeByPrimaryKeySelective(@PathVariable Long id, @RequestBody CodeVO codeVO){
		CodeModel codeModel = beanMapper.map(codeVO, CodeModel.class);
		codeModel.setId(id);
		Integer  result = codeService.updateByPrimaryKeySelective(codeModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
