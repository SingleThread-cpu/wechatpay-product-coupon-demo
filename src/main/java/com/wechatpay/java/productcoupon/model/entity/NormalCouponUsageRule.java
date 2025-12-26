package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 满减券使用规则
 */
@Data
public class NormalCouponUsageRule {
    @SerializedName("threshold")
    private Long threshold;

    @SerializedName("discount_amount")
    private Long discountAmount;
}
