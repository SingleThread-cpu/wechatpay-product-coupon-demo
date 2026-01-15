package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 兑换券使用规则
 */
@Data
public class ExchangeCouponUsageRule {
    @SerializedName("threshold")
    private Long threshold;

    @SerializedName("exchange_price")
    private Long exchangePrice;
}
