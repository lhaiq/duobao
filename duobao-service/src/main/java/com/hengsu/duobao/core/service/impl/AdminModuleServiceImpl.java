package com.hengsu.duobao.core.service.impl;

import com.hengsu.duobao.core.model.ModuleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.duobao.core.entity.AdminModule;
import com.hengsu.duobao.core.repository.AdminModuleRepository;
import com.hengsu.duobao.core.model.AdminModuleModel;
import com.hengsu.duobao.core.service.AdminModuleService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class AdminModuleServiceImpl implements AdminModuleService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private AdminModuleRepository adminModuleRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public int create(AdminModuleModel adminModuleModel) {
        return adminModuleRepo.insert(beanMapper.map(adminModuleModel, AdminModule.class));
    }

    @Transactional
    @Override
    public int createSelective(AdminModuleModel adminModuleModel) {
        return adminModuleRepo.insertSelective(beanMapper.map(adminModuleModel, AdminModule.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return adminModuleRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public AdminModuleModel findByPrimaryKey(Long id) {
        AdminModule adminModule = adminModuleRepo.selectByPrimaryKey(id);
        return beanMapper.map(adminModule, AdminModuleModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(AdminModuleModel adminModuleModel) {
        return adminModuleRepo.selectCount(beanMapper.map(adminModuleModel, AdminModule.class));
    }

    @Override
    @Transactional
    public void updatePermission(Long adminId, List<Long> moduleIds) {

        //先删除以前的权限
        String sql = "delete from admin_module where admin_id = ?";
        jdbcTemplate.update(sql, adminId);

        //加新的权限
        for (Long moduleId : moduleIds) {
            AdminModuleModel adminModuleModel = new AdminModuleModel();
            adminModuleModel.setAdminId(adminId);
            adminModuleModel.setModuleId(moduleId);
            createSelective(adminModuleModel);
        }

    }

    @Override
    public boolean hashPermission(Long adminId, String url) {
        String sql = "select count(1) from admin_module where admin_id=? and module_id = ?";
        long count =1;//TODO
        return count == 0 ? false : true;
    }

    @Override
    public List<String> findPermissions(Long adminId) {
        String sql="select * from module ";
        return null;
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(AdminModuleModel adminModuleModel) {
        return adminModuleRepo.updateByPrimaryKey(beanMapper.map(adminModuleModel, AdminModule.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(AdminModuleModel adminModuleModel) {
        return adminModuleRepo.updateByPrimaryKeySelective(beanMapper.map(adminModuleModel, AdminModule.class));
    }

}
