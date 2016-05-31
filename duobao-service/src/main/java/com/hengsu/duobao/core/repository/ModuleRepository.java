package com.hengsu.duobao.core.repository;

import com.hengsu.duobao.core.entity.Module;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("module") Module module);

    int insertSelective(@Param("module") Module module);

    Module selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("module") Module module);

    int updateByPrimaryKey(@Param("module") Module module);

    int selectCount(@Param("module") Module module);

    List<Module> selectPage(@Param("module") Module module, @Param("pageable") Pageable pageable);
}