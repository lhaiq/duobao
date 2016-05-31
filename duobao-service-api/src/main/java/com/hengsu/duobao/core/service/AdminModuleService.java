
package com.hengsu.duobao.core.service;

import com.hengsu.duobao.core.model.AdminModuleModel;
import com.hengsu.duobao.core.model.ModuleModel;

import java.util.List;

public interface AdminModuleService {

    public int create(AdminModuleModel adminModuleModel);

    public int createSelective(AdminModuleModel adminModuleModel);

    public AdminModuleModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(AdminModuleModel adminModuleModel);

    public int updateByPrimaryKeySelective(AdminModuleModel adminModuleModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(AdminModuleModel adminModuleModel);

    public void updatePermission(Long adminId, List<Long> moduleIds);

    public boolean hashPermission(Long adminId,String url);

    public List<String> findPermissions(Long adminId);


}