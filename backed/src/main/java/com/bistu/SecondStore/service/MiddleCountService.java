package com.bistu.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.SecondStore.entity.MiddleCount;

import java.io.Serializable;

/**
 * 中间用户(MiddleCount)表服务接口
 */
public interface MiddleCountService extends IService<MiddleCount> {
    MiddleCount getMiddleCountByOrderId(Serializable orderId);
}