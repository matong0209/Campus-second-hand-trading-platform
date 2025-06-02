package com.bistu.SecondStore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.SecondStore.entity.Orders;
import com.bistu.SecondStore.entity.vo.OrdersInfo;

import java.io.Serializable;

/**
 * 订单(Order)表服务接口
 */
public interface OrderService extends IService<Orders> {
    IPage<OrdersInfo> pageByStatus(IPage<OrdersInfo> page, Serializable status);
}