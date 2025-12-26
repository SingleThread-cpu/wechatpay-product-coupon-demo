package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 批次发放规则
 */
@Data
public class StockSendRule {
    @SerializedName("max_count")
    private Long maxCount;

    @SerializedName("max_count_per_day")
    private Long maxCountPerDay;

    @SerializedName("max_count_per_user")
    private Long maxCountPerUser;
}
