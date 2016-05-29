package com.hengsu.duobao.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import com.hengsu.duobao.mall.service.CategoryService;
import com.hengsu.duobao.mall.model.CategoryModel;
import com.hengsu.duobao.mall.vo.CategoryVO;

import java.util.List;

@RestApiController
@RequestMapping("/duobao")
public class CategoryRestApiController {

	private final Logger logger = LoggerFactory.getLogger(CategoryRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private CategoryService categoryService;

	/**
	 * 商品类型详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/mall/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<CategoryVO>> getCategoryById(@PathVariable Long id){
		CategoryModel categoryModel = categoryService.findByPrimaryKey(id);
		CategoryVO categoryVO =beanMapper.map(categoryModel, CategoryVO.class);
		ResponseEnvelope<CategoryVO> responseEnv = new ResponseEnvelope<>(categoryVO,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 商品类型列表
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/mall/categories", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<Page<CategoryModel> >> listCategories(Pageable pageable){
		CategoryModel param = new CategoryModel();
		List<CategoryModel> categoryModels = categoryService.selectPage(param,pageable);
		int count=categoryService.selectCount(param);
		Page<CategoryModel> page = new PageImpl<>(categoryModels,pageable,count);
		ResponseEnvelope<Page<CategoryModel> > responseEnv = new ResponseEnvelope<>(page,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 添加商品类型
	 * @param categoryVO
	 * @return
	 */
	@RequestMapping(value = "/mall/category", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createCategory(@RequestBody CategoryVO categoryVO){
		CategoryModel categoryModel = beanMapper.map(categoryVO, CategoryModel.class);
		Integer  result = categoryService.createSelective(categoryModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}


	/**
	 * 删除商品类型
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/mall/category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteCategoryByPrimaryKey(@PathVariable Long id){
		Integer  result = categoryService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 修改商品类型
	 * @param id
	 * @param categoryVO
	 * @return
	 */
	@RequestMapping(value = "/mall/category/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateCategoryByPrimaryKeySelective(@PathVariable Long id, @RequestBody CategoryVO categoryVO){
		CategoryModel categoryModel = beanMapper.map(categoryVO, CategoryModel.class);
		categoryModel.setId(id);
		Integer  result = categoryService.updateByPrimaryKeySelective(categoryModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
