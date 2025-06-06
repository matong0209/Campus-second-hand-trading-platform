package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SecondStore.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 普通用户(User)表数据库访问层
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}