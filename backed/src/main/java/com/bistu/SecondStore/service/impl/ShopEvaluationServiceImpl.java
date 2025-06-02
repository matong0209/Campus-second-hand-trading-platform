package com.bistu.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.SecondStore.dao.ShopEvaluationDao;
import com.bistu.SecondStore.entity.ShopEvaluation;
import com.bistu.SecondStore.service.ShopEvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺评价(ShopEvaluation)表服务实现类
 */
@Slf4j
@Service("shopEvaluationService")
public class ShopEvaluationServiceImpl extends ServiceImpl<ShopEvaluationDao, ShopEvaluation> implements ShopEvaluationService {

    @Override
    public List<ShopEvaluation> getEvaluationsByShopId(Serializable shopId) {
        return list(new LambdaQueryWrapper<ShopEvaluation>().eq(ShopEvaluation::getShopId, shopId));
    }

    @Override
    public Double calculateScore(int shopId) {
        return baseMapper.calculateScore(shopId);
    }
}