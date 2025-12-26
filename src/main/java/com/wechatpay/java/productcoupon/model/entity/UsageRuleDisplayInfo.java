package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import com.wechatpay.java.productcoupon.model.enums.CouponUsageMethod;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用规则展示信息
 */
@Data
public class UsageRuleDisplayInfo {
    @SerializedName("coupon_usage_method_list")
    private List<CouponUsageMethod> couponUsageMethodList = new ArrayList<>();

    @SerializedName("mini_program_appid")
    private String miniProgramAppid;

    @SerializedName("mini_program_path")
    private String miniProgramPath;

    @SerializedName("app_path")
    private String appPath;

    @SerializedName("usage_description")
    private String usageDescription;

    @SerializedName("coupon_available_store_info")
    private CouponAvailableStoreInfo couponAvailableStoreInfo;
}
