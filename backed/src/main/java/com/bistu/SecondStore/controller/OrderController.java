package com.bistu.SecondStore.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.SecondStore.dao.RateDao;
import com.bistu.SecondStore.entity.*;
import com.bistu.SecondStore.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 订单(Order)表控制层
 *
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private SellService sellService;
    @Autowired
    private MiddleCountService middleCountService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private RateDao rateDao;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.orderService.getById(id));
    }

    @GetMapping("user/{userId}")
    public R selectByUserID(@RequestParam("page") long page, @PathVariable Serializable userId) {
        return success(this.orderService.page(new Page<>(page, 20),
                new LambdaQueryWrapper<Orders>().eq(Orders::getUserId, userId)));
    }

    @GetMapping("shop/{shopId}")
    public R selectByShopID(@RequestParam("page") long page, @PathVariable Serializable shopId) {
        return success(this.orderService.page(new Page<>(page, 30),
                new LambdaQueryWrapper<Orders>().eq(Orders::getShopId, shopId).between(Orders::getStatus,2,8)));
    }

    @GetMapping
    public R selectAll(@RequestParam("page") long page){
        return success(this.orderService.pageByStatus(new Page<>(page, 30),8));
    }

    /**
     * 修改数据
     *
     * @param orders 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public R update(@RequestBody Orders orders) {
        if (orders.getStatus() == 8) {
            MiddleCount middleCount = middleCountService.getMiddleCountByOrderId(orders.getId());
            if (middleCount != null) {
                // 更新用户积分
                User user = userService.getById(orders.getUserId());
                user.setPoints(user.getPoints() + middleCount.getAmount());
                userService.updateById(user);

                // 更新卖家钱包
                Sell sell = sellService.getById(middleCount.getSellerId());
                if (sell != null) {
                    Integer level = sell.getLevel();
                    sell.setWallet(sell.getWallet() + middleCount.getAmount() *
                            (1 - rateDao.selectById(level).getRate()));
                    sellService.updateById(sell);
                }

            }
        } else if (orders.getCanReturn() == 1 && (orders.getStatus() == 5 || orders.getStatus() == 6)) {
            return failed("支付超过24h,无法退货");
        } else if (orders.getStatus() == 6) {
            MiddleCount middleCount = middleCountService.getMiddleCountByOrderId(orders.getId());
            if (middleCount != null) {
                User user = userService.getById(orders.getUserId());
                user.setWallet(user.getWallet() + middleCount.getAmount());
                userService.updateById(user);
                middleCountService.removeById(middleCount.getId());
            }
        }

        // 如果订单状态为取消支付或已退货，需要恢复商品库存
        if (orders.getStatus() == 1 || orders.getStatus() == 6) {
            Commodity commodity = commodityService.getById(orders.getCommodityId());
            commodity.setInventory(commodity.getInventory() + orders.getQuantity());
            commodityService.updateById(commodity);
        }

        // 更新订单信息
        return success(this.orderService.updateById(orders));
    }



    @PostMapping("pay")
    public R payOrders(@RequestBody List<Orders> orders) {
        User user = userService.getById(orders.get(0).getUserId());
        double sum = orders.stream().mapToDouble(Orders::getAmount).sum();
        if (user.getWallet() < sum) {
            return failed("余额不足");
        }
        for (Orders order : orders) {
            MiddleCount middleCount = new MiddleCount();
            middleCount.setOrderId(order.getId());
            middleCount.setUserId(user.getId());
            middleCount.setAmount(order.getAmount());
            middleCount.setSellerId(shopService.getById(order.getShopId()).getSellerId());
            middleCountService.save(middleCount);
            user.setWallet(user.getWallet() - order.getAmount());
            order.setStatus(2);
            orderService.updateById(order);
        }
        return success(userService.updateById(user));
    }
}