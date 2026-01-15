package com.wechatpay.java.productcoupon.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 核销方式
 */
public enum CouponUsageMethod {
    @SerializedName("OFFLINE")
    OFFLINE,
    @SerializedName("MINI_PROGRAM")
    MINI_PROGRAM,
    @SerializedName("APP")
    APP,
    @SerializedName("PAYMENT_CODE")
    PAYMENT_CODE
}

