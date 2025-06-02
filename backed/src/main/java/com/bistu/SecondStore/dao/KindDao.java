package com.bistu.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bistu.SecondStore.entity.Kind;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类别(Kind)表数据库访问层
 *
 */
@Mapper
public interface KindDao extends BaseMapper<Kind> {

}