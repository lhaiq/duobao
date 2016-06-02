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

import com.hengsu.duobao.core.service.RecommendRelationService;
import com.hengsu.duobao.core.model.RecommendRelationModel;
import com.hengsu.duobao.core.vo.RecommendRelationVO;

@RestApiController
@RequestMapping("/duobao")
public class RecommendRelationRestApiController {

	private final Logger logger = LoggerFactory.getLogger(RecommendRelationRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private RecommendRelationService recommendRelationService;
	
	@RequestMapping(value = "/core/recommendRelation/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<RecommendRelationVO>> getRecommendRelationById(@PathVariable Long id){
		RecommendRelationModel recommendRelationModel = recommendRelationService.findByPrimaryKey(id);
		RecommendRelationVO recommendRelationVO =beanMapper.map(recommendRelationModel, RecommendRelationVO.class);
		ResponseEnvelope<RecommendRelationVO> responseEnv = new ResponseEnvelope<RecommendRelationVO>(recommendRelationVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/recommendRelation", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createRecommendRelation(@RequestBody RecommendRelationVO recommendRelationVO){
		RecommendRelationModel recommendRelationModel = beanMapper.map(recommendRelationVO, RecommendRelationModel.class);
		Integer  result = recommendRelationService.create(recommendRelationModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/recommendRelation/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteRecommendRelationByPrimaryKey(@PathVariable Long id){
		Integer  result = recommendRelationService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/recommendRelation/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateRecommendRelationByPrimaryKeySelective(@PathVariable Long id, @RequestBody RecommendRelationVO recommendRelationVO){
		RecommendRelationModel recommendRelationModel = beanMapper.map(recommendRelationVO, RecommendRelationModel.class);
		recommendRelationModel.setId(id);
		Integer  result = recommendRelationService.updateByPrimaryKeySelective(recommendRelationModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
