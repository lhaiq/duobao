
package com.hengsu.duobao.core.service;

import com.hengsu.duobao.core.model.AddressModel;

public interface AddressService{
	
	public int create(AddressModel addressModel);
	
	public int createSelective(AddressModel addressModel);
	
	public AddressModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(AddressModel addressModel);
	
	public int updateByPrimaryKeySelective(AddressModel addressModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(AddressModel addressModel);
	
}