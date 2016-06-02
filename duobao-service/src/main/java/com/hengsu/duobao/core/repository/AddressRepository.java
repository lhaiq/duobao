package com.hengsu.duobao.core.repository;

import com.hengsu.duobao.core.entity.Address;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("address") Address address);

    int insertSelective(@Param("address") Address address);

    Address selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("address") Address address);

    int updateByPrimaryKey(@Param("address") Address address);

    int selectCount(@Param("address") Address address);

    List<Address> selectPage(@Param("address") Address address, @Param("pageable") Pageable pageable);
}