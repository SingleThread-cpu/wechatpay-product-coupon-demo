package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 单券使用规则
 */
@Data
public class SingleUsageRule {
    @SerializedName("coupon_available_period")
    private CouponAvailablePeriod couponAvailablePeriod;

    @SerializedName("normal_coupon")
    private NormalCouponUsageRule normalCoupon;

    @SerializedName("discount_coupon")
    private DiscountCouponUsageRule discountCoupon;

    @SerializedName("exchange_coupon")
    private ExchangeCouponUsageRule exchangeCoupon;
}
