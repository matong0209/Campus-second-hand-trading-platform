package com.bistu.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.SecondStore.dao.CommodityPhotoDao;
import com.bistu.SecondStore.entity.CommodityPhoto;
import com.bistu.SecondStore.service.CommodityPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 商品图片(CommodityPhoto)表服务实现类
 */
@Slf4j
@Service("commodityPhotoService")
public class CommodityPhotoServiceImpl extends ServiceImpl<CommodityPhotoDao, CommodityPhoto> implements CommodityPhotoService {

    @Override
    public List<CommodityPhoto> getPhotoByCommodityId(Serializable commodityId) {
        return list(new LambdaQueryWrapper<CommodityPhoto>().eq(CommodityPhoto::getCommodityId, commodityId));
    }
}