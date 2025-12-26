package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 多次优惠使用规则
 */
@Data
public class ProgressiveBundleUsageRule {
    @SerializedName("coupon_available_period")
    private CouponAvailablePeriod couponAvailablePeriod;

    @SerializedName("special_first")
    private Boolean specialFirst;

    @SerializedName("normal_coupon")
    private NormalCouponUsageRule normalCoupon;

    @SerializedName("discount_coupon")
    private DiscountCouponUsageRule discountCoupon;

    @SerializedName("exchange_coupon")
    private ExchangeCouponUsageRule exchangeCoupon;

    @SerializedName("normal_coupon_list")
    private List<NormalCouponUsageRule> normalCouponList;

    @SerializedName("discount_coupon_list")
    private List<DiscountCouponUsageRule> discountCouponList;

    @SerializedName("exchange_coupon_list")
    private List<ExchangeCouponUsageRule> exchangeCouponList;
}
