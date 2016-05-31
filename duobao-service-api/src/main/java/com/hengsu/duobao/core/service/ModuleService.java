
package com.hengsu.duobao.core.service;

import com.hengsu.duobao.core.model.ModuleModel;

import java.util.List;

public interface ModuleService{
	
	public int create(ModuleModel moduleModel);
	
	public int createSelective(ModuleModel moduleModel);
	
	public ModuleModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ModuleModel moduleModel);
	
	public int updateByPrimaryKeySelective(ModuleModel moduleModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(ModuleModel moduleModel);

}