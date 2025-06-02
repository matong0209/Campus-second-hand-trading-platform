package com.example.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecondStore.entity.Sell;

import java.io.Serializable;

/**
 * 商家(Sell)表服务接口
 */
public interface SellService extends IService<Sell> {
    Sell register(Serializable name);

    Sell login(Serializable name, Serializable password);
}