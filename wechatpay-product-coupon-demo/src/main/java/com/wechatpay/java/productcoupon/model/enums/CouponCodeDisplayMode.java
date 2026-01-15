package com.wechatpay.java.productcoupon.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 券码展示模式
 */
public enum CouponCodeDisplayMode {
    @SerializedName("INVISIBLE")
    INVISIBLE,
    @SerializedName("BARCODE")
    BARCODE,
    @SerializedName("QRCODE")
    QRCODE
}

