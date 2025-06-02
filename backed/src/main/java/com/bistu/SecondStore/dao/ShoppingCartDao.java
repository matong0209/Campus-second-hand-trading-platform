package com.bistu.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bistu.SecondStore.entity.ShoppingCart;
import com.bistu.SecondStore.entity.vo.ShoppingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车(ShoppingCart)表数据库访问层
 */
@Mapper
public interface ShoppingCartDao extends BaseMapper<ShoppingCart> {
    /**
     * 根据用户 ID 查询购物车信息
     */
    @Select("select * from shopping_cart s join commodity c on c.id = s.commodity_id where s.user_id = #{userId}")
    List<ShoppingInfo> getShoppingCartByUserId(IPage<ShoppingInfo> page, Serializable userId);
}