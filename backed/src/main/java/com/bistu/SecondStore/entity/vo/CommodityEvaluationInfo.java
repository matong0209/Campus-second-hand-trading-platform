package com.bistu.SecondStore.entity.vo;

import com.bistu.SecondStore.entity.CommodityEvaluation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommodityEvaluationInfo extends CommodityEvaluation {
    private String name;    //userName
}
