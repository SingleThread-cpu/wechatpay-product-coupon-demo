package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 单券模式信息
 */
@Data
public class SingleUsageInfo {
    @SerializedName("normal_coupon")
    private NormalCouponUsageRule normalCoupon;

    @SerializedName("discount_coupon")
    private DiscountCouponUsageRule discountCoupon;
}
