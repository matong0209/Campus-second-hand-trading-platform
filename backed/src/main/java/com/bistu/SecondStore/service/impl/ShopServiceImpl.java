package com.bistu.SecondStore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.SecondStore.dao.ShopDao;
import com.bistu.SecondStore.entity.Shop;
import com.bistu.SecondStore.service.ShopService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 店铺(Shop)表服务实现类
 */
@Slf4j
@Service("shopService")
public class ShopServiceImpl extends ServiceImpl<ShopDao, Shop> implements ShopService {
}