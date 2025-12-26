package com.wechatpay.java.productcoupon.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 使用模式
 */
public enum UsageMode {
    @SerializedName("SINGLE")
    SINGLE,
    @SerializedName("PROGRESSIVE_BUNDLE")
    PROGRESSIVE_BUNDLE
}

