package com.hengsu.duobao.core.repository;

import com.hengsu.duobao.core.entity.AdminModule;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminModuleRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("adminmodule") AdminModule adminmodule);

    int insertSelective(@Param("adminmodule") AdminModule adminmodule);

    AdminModule selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("adminmodule") AdminModule adminmodule);

    int updateByPrimaryKey(@Param("adminmodule") AdminModule adminmodule);

    int selectCount(@Param("adminmodule") AdminModule adminmodule);

    List<AdminModule> selectPage(@Param("adminmodule") AdminModule adminmodule, @Param("pageable") Pageable pageable);
}