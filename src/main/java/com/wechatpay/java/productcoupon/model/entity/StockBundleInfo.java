package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 批次组信息
 */
@Data
public class StockBundleInfo {
    @SerializedName("stock_bundle_id")
    private String stockBundleId;

    @SerializedName("stock_bundle_index")
    private Long stockBundleIndex;
}
