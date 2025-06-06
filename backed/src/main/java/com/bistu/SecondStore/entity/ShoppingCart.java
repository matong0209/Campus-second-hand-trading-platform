package com.bistu.SecondStore.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 购物车(ShoppingCart)表实体类
 */
@Data
public class ShoppingCart implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    private Integer commodityId;
    
    private Integer quantity;
    
    private Integer userId;
    
}