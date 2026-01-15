package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import com.wechatpay.java.productcoupon.model.enums.WeekEnum;
import lombok.Data;

import java.util.List;

/**
 * 每周固定可用时间
 */
@Data
public class FixedWeekPeriod {
    @SerializedName("day_list")
    private List<WeekEnum> dayList;

    @SerializedName("day_period_list")
    private List<PeriodOfTheDay> dayPeriodList;
}
