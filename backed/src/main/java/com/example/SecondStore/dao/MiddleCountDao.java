package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SecondStore.entity.MiddleCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * 中间用户(MiddleCount)表数据库访问层
 *
 */
@Mapper
public interface MiddleCountDao extends BaseMapper<MiddleCount> {

}