package com.bistu.SecondStore.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.SecondStore.entity.Commodity;
import com.bistu.SecondStore.entity.Orders;
import com.bistu.SecondStore.entity.ShoppingCart;
import com.bistu.SecondStore.service.CommodityService;
import com.bistu.SecondStore.service.ShoppingCartService;
import com.bistu.SecondStore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车(ShoppingCart)表控制层
 *
 */
@Slf4j
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController extends ApiController{
    /**
     * 服务对象
     */
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private OrderService orderService;

    @GetMapping("{userId}")
    public R getShopCraftById(@PathVariable Serializable userId, @RequestParam("page") long page) {
        return success(this.shoppingCartService.page(new Page<>(page, 10), userId));
    }

    /**
     * 新增数据
     *
     * @param shoppingCart 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart oldShoppingCart = shoppingCartService.getShoppingCartByCommodityId(shoppingCart);
        Commodity commodity = commodityService.getById(shoppingCart.getCommodityId());
        if (commodity == null) return failed("非法参数");

        if (oldShoppingCart == null) {
//            if (shoppingCart.getQuantity() > commodity.getInventory()) {
//                return failed("库存不足");
//            }
            return success(this.shoppingCartService.save(shoppingCart));
        }

        int quantity = shoppingCart.getQuantity() + oldShoppingCart.getQuantity();
//        if (quantity > commodity.getInventory()) {
//            return failed("库存不足");
//        }
        oldShoppingCart.setQuantity(quantity);
        return update(oldShoppingCart);
    }

    /**
     * 修改数据
     *
     * @param shoppingCart 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public R update(@RequestBody ShoppingCart shoppingCart) {
        if (shoppingCart.getQuantity() <= 0) {
            return success(this.shoppingCartService.removeById(shoppingCart.getId()));
        }
        return success(this.shoppingCartService.updateById(shoppingCart));
    }

    /**
     * 批量删除购物车
     * @param shoppingCarts
     * @return
     */
    @PostMapping("delete")
    public  R delete(@RequestBody List<ShoppingCart> shoppingCarts){
        if(shoppingCarts.size()>0){
            for (ShoppingCart shoppingCart : shoppingCarts) {
                this.shoppingCartService.removeById(shoppingCart.getId());
            }
            return success(true);
        }
        return failed("操作失败");
    }

    @PostMapping("checkOut")
    public R checkOutShoppingCart(@RequestBody List<ShoppingCart> shoppingCarts) {
        // 初始化订单和商品列表
        List<Orders> orders = new ArrayList<>();
        List<Commodity> commodities = new ArrayList<>();

        // 遍历购物车中的每个商品项
        for (ShoppingCart shoppingCart : shoppingCarts) {
            // 根据商品ID查询商品信息
            Commodity commodity = commodityService.getById(shoppingCart.getCommodityId());

            // 计算剩余库存
            int inventory = commodity.getInventory() - shoppingCart.getQuantity();
            if (inventory < 0) {
                return failed("库存不足");
            }

            // 更新商品库存
            commodity.setInventory(inventory);
            commodities.add(commodity);

            // 创建订单项
            Orders order = new Orders();
            order.setCommodityId(commodity.getId());
            order.setQuantity(shoppingCart.getQuantity());

            // 计算订单金额
            if (commodity.getDiscount() == null || commodity.getDiscount() == 0.0) {
                order.setAmount(commodity.getPrice() * shoppingCart.getQuantity());
            } else {
                double amount = commodity.getDiscount() * commodity.getPrice() * shoppingCart.getQuantity();
                // 对计算结果保留三位小数，四舍五入
                amount = new BigDecimal(amount).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                order.setAmount(amount);
            }

            // 设置订单的用户ID和商铺ID
            order.setUserId(shoppingCart.getUserId());
            order.setShopId(commodity.getShopId());

            // 将订单和商品添加到对应的列表中
            orders.add(order);
        }

        // 批量保存订单
        orderService.saveBatch(orders);

        // 批量更新商品库存
        commodityService.updateBatchById(commodities);

        // 返回成功响应，并携带订单信息
        return success(orders);
    }
}