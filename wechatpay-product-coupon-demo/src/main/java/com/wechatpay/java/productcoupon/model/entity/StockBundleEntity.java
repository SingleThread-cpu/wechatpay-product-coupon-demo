package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 批次组实体
 */
@Data
public class StockBundleEntity {
    @SerializedName("stock_bundle_id")
    private String stockBundleId;

    @SerializedName("stock_list")
    private List<StockEntityInBundle> stockList;
}
