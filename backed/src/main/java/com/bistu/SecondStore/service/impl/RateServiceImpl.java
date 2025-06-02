package com.bistu.SecondStore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.SecondStore.dao.RateDao;
import com.bistu.SecondStore.entity.Rate;
import com.bistu.SecondStore.service.RateService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (Rate)表服务实现类
 */
@Slf4j
@Service("rateService")
public class RateServiceImpl extends ServiceImpl<RateDao, Rate> implements RateService {

}