package com.bistu.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bistu.SecondStore.entity.Orders;
import com.bistu.SecondStore.entity.vo.OrdersInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * 订单(Order)表数据库访问层
 */
@Mapper
public interface OrderDao extends BaseMapper<Orders> {

    @Select("select * from orders o join sell s on o.shop_id = s.id where o.status = #{status}")
    List<OrdersInfo> getOrdersByStatus(IPage<OrdersInfo> page, Serializable status);
}