
package com.hengsu.duobao.mall.service;

import com.hengsu.duobao.mall.model.CategoryModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService{
	
	public int create(CategoryModel categoryModel);
	
	public int createSelective(CategoryModel categoryModel);
	
	public CategoryModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(CategoryModel categoryModel);
	
	public int updateByPrimaryKeySelective(CategoryModel categoryModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(CategoryModel categoryModel);

	public List<CategoryModel> selectPage(CategoryModel categoryModel,Pageable pageable);

}