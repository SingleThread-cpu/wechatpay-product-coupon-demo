package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 时间段
 */
@Data
public class TimePeriod {
    @SerializedName("begin_time")
    private String beginTime;

    @SerializedName("end_time")
    private String endTime;
}
