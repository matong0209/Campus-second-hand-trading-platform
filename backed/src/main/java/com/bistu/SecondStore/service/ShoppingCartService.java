package com.bistu.SecondStore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.SecondStore.entity.ShoppingCart;
import com.bistu.SecondStore.entity.vo.ShoppingInfo;

import java.io.Serializable;

/**
 * 购物车(ShoppingCart)表服务接口
 */
public interface ShoppingCartService extends IService<ShoppingCart> {

    ShoppingCart getShoppingCartByCommodityId(ShoppingCart shopCraft);

    IPage<ShoppingInfo> page(IPage<ShoppingInfo> page, Serializable userId);
}