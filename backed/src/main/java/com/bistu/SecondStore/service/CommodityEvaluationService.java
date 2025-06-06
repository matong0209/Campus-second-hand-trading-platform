package com.bistu.SecondStore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.SecondStore.entity.CommodityEvaluation;
import com.bistu.SecondStore.entity.vo.CommodityEvaluationInfo;

import java.io.Serializable;

/**
 * 商品评价(CommodityEvaluation)表服务接口
 */
public interface CommodityEvaluationService extends IService<CommodityEvaluation> {
    Double calculateScore(int commodityId);
    Integer evaluationCount(int commodityId);
    IPage<CommodityEvaluationInfo> getEvaluationById(IPage<CommodityEvaluationInfo> page, Serializable commodityId);
}