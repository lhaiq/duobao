package com.hengsu.duobao.core.controller;

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

import com.hengsu.duobao.core.service.AddressService;
import com.hengsu.duobao.core.model.AddressModel;
import com.hengsu.duobao.core.vo.AddressVO;

@RestApiController
@RequestMapping("/duobao")
public class AddressRestApiController {

	private final Logger logger = LoggerFactory.getLogger(AddressRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value = "/core/address/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<AddressVO>> getAddressById(@PathVariable Long id){
		AddressModel addressModel = addressService.findByPrimaryKey(id);
		AddressVO addressVO =beanMapper.map(addressModel, AddressVO.class);
		ResponseEnvelope<AddressVO> responseEnv = new ResponseEnvelope<AddressVO>(addressVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/address", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createAddress(@RequestBody AddressVO addressVO){
		AddressModel addressModel = beanMapper.map(addressVO, AddressModel.class);
		Integer  result = addressService.create(addressModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/address/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteAddressByPrimaryKey(@PathVariable Long id){
		Integer  result = addressService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/address/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateAddressByPrimaryKeySelective(@PathVariable Long id, @RequestBody AddressVO addressVO){
		AddressModel addressModel = beanMapper.map(addressVO, AddressModel.class);
		addressModel.setId(id);
		Integer  result = addressService.updateByPrimaryKeySelective(addressModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
