package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SecondStore.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

/**
 * 店铺(Shop)表数据库访问层
 */
@Mapper
public interface ShopDao extends BaseMapper<Shop> {

}