package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import com.wechatpay.java.productcoupon.model.enums.CouponCodeMode;
import com.wechatpay.java.productcoupon.model.enums.StockStoreScope;
import lombok.Data;

/**
 * 批次创建信息（单券模式）
 */
@Data
public class StockForCreate {
    @SerializedName("remark")
    private String remark;

    @SerializedName("coupon_code_mode")
    private CouponCodeMode couponCodeMode;

    @SerializedName("stock_send_rule")
    private StockSendRule stockSendRule;

    @SerializedName("single_usage_rule")
    private SingleUsageRule singleUsageRule;

    @SerializedName("usage_rule_display_info")
    private UsageRuleDisplayInfo usageRuleDisplayInfo;

    @SerializedName("coupon_display_info")
    private CouponDisplayInfo couponDisplayInfo;

    @SerializedName("notify_config")
    private NotifyConfig notifyConfig;

    @SerializedName("store_scope")
    private StockStoreScope storeScope;
}
