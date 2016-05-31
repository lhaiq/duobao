package com.hengsu.duobao.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.duobao.core.entity.Module;
import com.hengsu.duobao.core.repository.ModuleRepository;
import com.hengsu.duobao.core.model.ModuleModel;
import com.hengsu.duobao.core.service.ModuleService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ModuleRepository moduleRepo;

	@Transactional
	@Override
	public int create(ModuleModel moduleModel) {
		return moduleRepo.insert(beanMapper.map(moduleModel, Module.class));
	}

	@Transactional
	@Override
	public int createSelective(ModuleModel moduleModel) {
		return moduleRepo.insertSelective(beanMapper.map(moduleModel, Module.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return moduleRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public ModuleModel findByPrimaryKey(Long id) {
		Module module = moduleRepo.selectByPrimaryKey(id);
		return beanMapper.map(module, ModuleModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(ModuleModel moduleModel) {
		return moduleRepo.selectCount(beanMapper.map(moduleModel, Module.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(ModuleModel moduleModel) {
		return moduleRepo.updateByPrimaryKey(beanMapper.map(moduleModel, Module.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(ModuleModel moduleModel) {
		return moduleRepo.updateByPrimaryKeySelective(beanMapper.map(moduleModel, Module.class));
	}

}
