package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.SecondStore.entity.CommodityEvaluation;
import com.example.SecondStore.entity.vo.CommodityEvaluationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * 商品评价(CommodityEvaluation)表数据库访问层
 *
 */
@Mapper
public interface CommodityEvaluationDao extends BaseMapper<CommodityEvaluation> {
    //根据商品ID查询该商品的平均评分
    @Select("select avg(score) from commodity_evaluation where commodity_id = #{commodityId}")
    Double calculateScore(int commodityId);

    //根据商品ID查询该商品的评价总数
    @Select("select count(*) from commodity_evaluation where commodity_id = #{commodityId}")
    Integer evaluationCount(int commodityId);

    //根据商品ID查询该商品的评价信息
    @Select("select * from commodity_evaluation c join user u on c.user_id = u.id where c.commodity_id = #{commodityId}")
    List<CommodityEvaluationInfo> getCommodityEvaluationById(IPage<CommodityEvaluationInfo> page, Serializable commodityId);
}