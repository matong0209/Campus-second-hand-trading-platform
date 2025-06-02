package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SecondStore.entity.ShopEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 店铺评价(ShopEvaluation)表数据库访问层
 */
@Mapper
public interface ShopEvaluationDao extends BaseMapper<ShopEvaluation> {
    @Select("select avg(score) from shop_evaluation where shop_id = #{shopId}")
    Double calculateScore(int shopId);
}