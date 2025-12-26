package com.wechatpay.java.productcoupon.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 商品券状态
 */
public enum ProductCouponState {
    @SerializedName("AUDITING")
    AUDITING,
    @SerializedName("EFFECTIVE")
    EFFECTIVE,
    @SerializedName("DEACTIVATED")
    DEACTIVATED
}

