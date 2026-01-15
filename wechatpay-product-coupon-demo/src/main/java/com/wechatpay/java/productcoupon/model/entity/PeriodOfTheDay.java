package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 当天可用时间段
 */
@Data
public class PeriodOfTheDay {
    @SerializedName("begin_time")
    private Long beginTime;

    @SerializedName("end_time")
    private Long endTime;
}
