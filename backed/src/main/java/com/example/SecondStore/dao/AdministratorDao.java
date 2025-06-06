package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SecondStore.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员(Administrator)表数据库访问层
 */
@Mapper
public interface AdministratorDao extends BaseMapper<Administrator> {
}