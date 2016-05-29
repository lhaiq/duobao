package com.hengsu.duobao.mall.repository;

import com.hengsu.duobao.mall.entity.Order;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("order") Order order);

    int insertSelective(@Param("order") Order order);

    Order selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("order") Order order);

    int updateByPrimaryKey(@Param("order") Order order);

    int selectCount(@Param("order") Order order);

    List<Order> selectPage(@Param("order") Order order, @Param("pageable") Pageable pageable);
}