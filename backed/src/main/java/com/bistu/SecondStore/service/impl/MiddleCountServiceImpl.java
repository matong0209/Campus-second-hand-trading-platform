package com.bistu.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.SecondStore.dao.MiddleCountDao;
import com.bistu.SecondStore.entity.MiddleCount;
import com.bistu.SecondStore.service.MiddleCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 中间用户(MiddleCount)表服务实现类
 */
@Slf4j
@Service("middleCountService")
public class MiddleCountServiceImpl extends ServiceImpl<MiddleCountDao, MiddleCount> implements MiddleCountService {

    @Override
    public MiddleCount getMiddleCountByOrderId(Serializable orderId) {
        return getOne(new LambdaQueryWrapper<MiddleCount>().eq(MiddleCount::getOrderId, orderId));
    }
}