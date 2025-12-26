package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 券码数量信息
 */
@Data
public class CouponCodeCountInfo {
    @SerializedName("total_count")
    private Long totalCount;

    @SerializedName("available_count")
    private Long availableCount;
}
