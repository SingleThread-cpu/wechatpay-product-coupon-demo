package com.wechatpay.java.productcoupon.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 门店适用范围
 */
public enum StockStoreScope {
    @SerializedName("NONE")
    NONE,
    @SerializedName("ALL")
    ALL,
    @SerializedName("SPECIFIC")
    SPECIFIC
}

