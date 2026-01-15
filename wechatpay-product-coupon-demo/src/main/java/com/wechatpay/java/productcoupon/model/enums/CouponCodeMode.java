package com.wechatpay.java.productcoupon.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 券码模式
 */
public enum CouponCodeMode {
    @SerializedName("WECHATPAY")
    WECHATPAY,
    @SerializedName("UPLOAD")
    UPLOAD,
    @SerializedName("API_ASSIGN")
    API_ASSIGN
}

