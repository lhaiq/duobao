
package com.hengsu.duobao.core.service;

import com.hengsu.duobao.core.model.AdminModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService{
	
	public int create(AdminModel adminModel);
	
	public int createSelective(AdminModel adminModel);
	
	public AdminModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(AdminModel adminModel);
	
	public int updateByPrimaryKeySelective(AdminModel adminModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(AdminModel adminModel);

	public AdminModel adminLogin(AdminModel adminModel);

	public List<AdminModel> selectPage(AdminModel adminModel,Pageable pageable);
	
}