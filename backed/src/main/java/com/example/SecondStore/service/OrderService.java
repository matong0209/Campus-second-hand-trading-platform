package com.example.SecondStore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecondStore.entity.Orders;
import com.example.SecondStore.entity.vo.OrdersInfo;

import java.io.Serializable;

/**
 * 订单(Order)表服务接口
 */
public interface OrderService extends IService<Orders> {
    IPage<OrdersInfo> pageByStatus(IPage<OrdersInfo> page, Serializable status);
}