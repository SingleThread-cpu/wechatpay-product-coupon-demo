package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import com.wechatpay.java.productcoupon.model.enums.CouponCodeMode;
import com.wechatpay.java.productcoupon.model.enums.StockState;
import com.wechatpay.java.productcoupon.model.enums.StockStoreScope;
import lombok.Data;

/**
 * 批次实体
 */
@Data
public class StockEntity {
    @SerializedName("product_coupon_id")
    private String productCouponId;

    @SerializedName("stock_id")
    private String stockId;

    @SerializedName("remark")
    private String remark;

    @SerializedName("coupon_code_mode")
    private CouponCodeMode couponCodeMode;

    @SerializedName("coupon_code_count_info")
    private CouponCodeCountInfo couponCodeCountInfo;

    @SerializedName("stock_send_rule")
    private StockSendRule stockSendRule;

    @SerializedName("single_usage_rule")
    private SingleUsageRule singleUsageRule;

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

    @SerializedName("sent_count_info")
    private StockSentCountInfo sentCountInfo;

    @SerializedName("state")
    private StockState state;

    @SerializedName("deactivate_request_no")
    private String deactivateRequestNo;

    @SerializedName("deactivate_time")
    private String deactivateTime;

    @SerializedName("deactivate_reason")
    private String deactivateReason;

    @SerializedName("brand_id")
    private String brandId;
}
