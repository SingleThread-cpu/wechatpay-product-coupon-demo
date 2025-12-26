package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 多次优惠模式配置信息
 */
@Data
public class ProgressiveBundleUsageInfo {
    @SerializedName("count")
    private Long count;

    @SerializedName("interval_days")
    private Long intervalDays;
}
