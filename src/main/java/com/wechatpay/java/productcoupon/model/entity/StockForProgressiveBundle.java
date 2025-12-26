package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import com.wechatpay.java.productcoupon.model.enums.CouponCodeMode;
import com.wechatpay.java.productcoupon.model.enums.StockStoreScope;
import lombok.Data;

/**
 * 批次创建信息（多次优惠模式）
 */
@Data
public class StockForProgressiveBundle {
    @SerializedName("remark")
    private String remark;

    @SerializedName("coupon_code_mode")
    private CouponCodeMode couponCodeMode;

    @SerializedName("stock_send_rule")
    private StockSendRule stockSendRule;

    @SerializedName("progressive_bundle_usage_rule")
    private ProgressiveBundleUsageRule progressiveBundleUsageRule;

    @SerializedName("usage_rule_display_info")
    private UsageRuleDisplayInfo usageRuleDisplayInfo;

    @SerializedName("coupon_display_info")
    private CouponDisplayInfo couponDisplayInfo;

    @SerializedName("notify_config")
    private NotifyConfig notifyConfig;

    @SerializedName("store_scope")
    private StockStoreScope storeScope;
}
