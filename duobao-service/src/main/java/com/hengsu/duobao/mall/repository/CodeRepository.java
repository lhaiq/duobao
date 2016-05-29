package com.hengsu.duobao.mall.repository;

import com.hengsu.duobao.mall.entity.Code;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("code") Code code);

    int insertSelective(@Param("code") Code code);

    Code selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("code") Code code);

    int updateByPrimaryKey(@Param("code") Code code);

    int selectCount(@Param("code") Code code);

    List<Code> selectPage(@Param("code") Code code, @Param("pageable") Pageable pageable);
}