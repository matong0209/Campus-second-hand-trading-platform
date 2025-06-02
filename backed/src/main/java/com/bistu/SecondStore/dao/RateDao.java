package com.bistu.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bistu.SecondStore.entity.Rate;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Rate)表数据库访问层
 */
@Mapper
public interface RateDao extends BaseMapper<Rate> {

}