package com.bistu.SecondStore.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bistu.SecondStore.dao.*;
import com.bistu.SecondStore.entity.Orders;
import com.bistu.SecondStore.entity.Sell;
import com.bistu.SecondStore.entity.User;
import com.bistu.SecondStore.entity.MiddleCount;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class MiddleCountTask {

    @Resource
    private MiddleCountDao middleCountDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private UserDao userDao;
    @Resource
    private SellDao sellDao;
    @Resource
    private RateDao rateDao;

    //3.添加定时任务
    @Scheduled(cron = "0/20 * * * * ?")
    private void configureTasks() {
        // 查询创建时间超过24小时的记录
        QueryWrapper<MiddleCount> queryWrapper = new QueryWrapper<MiddleCount>()
                .le("creat_time", LocalDateTime.now().minusHours(24));

        List<MiddleCount> middleCounts = middleCountDao.selectList(queryWrapper);
        if (middleCounts.isEmpty()) return;

        for (MiddleCount middleCount : middleCounts) {
            // 更新订单信息
            Orders orders = orderDao.selectById(middleCount.getOrderId());
            if (orders != null) {
                orders.setCanReturn(1); // 设置为不可退货
                orderDao.updateById(orders);
            }

        }
    }


}