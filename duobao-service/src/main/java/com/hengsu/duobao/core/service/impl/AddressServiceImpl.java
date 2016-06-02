package com.hengsu.duobao.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.duobao.core.entity.Address;
import com.hengsu.duobao.core.repository.AddressRepository;
import com.hengsu.duobao.core.model.AddressModel;
import com.hengsu.duobao.core.service.AddressService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private AddressRepository addressRepo;

	@Transactional
	@Override
	public int create(AddressModel addressModel) {
		return addressRepo.insert(beanMapper.map(addressModel, Address.class));
	}

	@Transactional
	@Override
	public int createSelective(AddressModel addressModel) {
		return addressRepo.insertSelective(beanMapper.map(addressModel, Address.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return addressRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public AddressModel findByPrimaryKey(Long id) {
		Address address = addressRepo.selectByPrimaryKey(id);
		return beanMapper.map(address, AddressModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(AddressModel addressModel) {
		return addressRepo.selectCount(beanMapper.map(addressModel, Address.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(AddressModel addressModel) {
		return addressRepo.updateByPrimaryKey(beanMapper.map(addressModel, Address.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(AddressModel addressModel) {
		return addressRepo.updateByPrimaryKeySelective(beanMapper.map(addressModel, Address.class));
	}

}
