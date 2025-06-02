package com.bistu.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.SecondStore.entity.Administrator;

import java.io.Serializable;

/**
 * 管理员(Administrator)表服务接口
 */
public interface AdministratorService extends IService<Administrator> {
    Administrator login(Serializable name, Serializable password);
}