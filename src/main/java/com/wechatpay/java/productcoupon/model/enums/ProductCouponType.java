package com.wechatpay.java.productcoupon.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 商品券类型
 */
public enum ProductCouponType {
    @SerializedName("NORMAL")
    NORMAL,
    @SerializedName("DISCOUNT")
    DISCOUNT,
    @SerializedName("EXCHANGE")
    EXCHANGE
}

