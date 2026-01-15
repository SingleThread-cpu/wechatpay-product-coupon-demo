package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 券可核销时间
 */
@Data
public class CouponAvailablePeriod {
    @SerializedName("available_begin_time")
    private String availableBeginTime;

    @SerializedName("available_end_time")
    private String availableEndTime;

    @SerializedName("available_days")
    private Long availableDays;

    @SerializedName("wait_days_after_receive")
    private Long waitDaysAfterReceive;

    @SerializedName("interval_days")
    private Long intervalDays;

    @SerializedName("weekly_available_period")
    private FixedWeekPeriod weeklyAvailablePeriod;

    @SerializedName("irregular_available_period_list")
    private List<TimePeriod> irregularAvailablePeriodList;
}
