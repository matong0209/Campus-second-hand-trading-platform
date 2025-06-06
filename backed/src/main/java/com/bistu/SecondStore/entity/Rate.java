package com.bistu.SecondStore.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * (Rate)表实体类
 */
@Data
public class Rate implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    //商家等级百分比
    private Double rate;
    
}