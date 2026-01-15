package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 批次发放数量信息
 */
@Data
public class StockSentCountInfo {
    @SerializedName("total_count")
    private Long totalCount;

    @SerializedName("today_count")
    private Long todayCount;
}
