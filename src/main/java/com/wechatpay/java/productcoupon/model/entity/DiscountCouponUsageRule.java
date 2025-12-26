package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 折扣券使用规则
 */
@Data
public class DiscountCouponUsageRule {
    @SerializedName("threshold")
    private Long threshold;

    @SerializedName("percent_off")
    private Long percentOff;
}
