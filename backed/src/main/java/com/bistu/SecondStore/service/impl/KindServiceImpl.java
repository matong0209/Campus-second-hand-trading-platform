package com.bistu.SecondStore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.SecondStore.dao.KindDao;
import com.bistu.SecondStore.entity.Kind;
import com.bistu.SecondStore.service.KindService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 类别(Kind)表服务实现类
 */
@Slf4j
@Service("kindService")
public class KindServiceImpl extends ServiceImpl<KindDao, Kind> implements KindService {

}