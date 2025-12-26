package com.wechatpay.java.productcoupon.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 批次状态
 */
public enum StockState {
    @SerializedName("AUDITING")
    AUDITING,
    @SerializedName("SENDING")
    SENDING,
    @SerializedName("PAUSED")
    PAUSED,
    @SerializedName("STOPPED")
    STOPPED,
    @SerializedName("DEACTIVATED")
    DEACTIVATED
}

