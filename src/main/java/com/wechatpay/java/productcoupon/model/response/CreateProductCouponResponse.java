package com.wechatpay.java.productcoupon.model.response;

import com.google.gson.annotations.SerializedName;
import com.wechatpay.java.productcoupon.model.entity.*;
import com.wechatpay.java.productcoupon.model.enums.ProductCouponScope;
import com.wechatpay.java.productcoupon.model.enums.ProductCouponState;
import com.wechatpay.java.productcoupon.model.enums.ProductCouponType;
import com.wechatpay.java.productcoupon.model.enums.UsageMode;
import lombok.Data;

/**
 * 创建商品券响应
 */
@Data
public class CreateProductCouponResponse {
    @SerializedName("product_coupon_id")
    private String productCouponId;

    @SerializedName("scope")
    private ProductCouponScope scope;

    @SerializedName("type")
    private ProductCouponType type;

    @SerializedName("usage_mode")
    private UsageMode usageMode;

    @SerializedName("single_usage_info")
    private SingleUsageInfo singleUsageInfo;

    @SerializedName("progressive_bundle_usage_info")
    private ProgressiveBundleUsageInfo progressiveBundleUsageInfo;

    @SerializedName("display_info")
    private ProductCouponDisplayInfo displayInfo;

    @SerializedName("out_product_no")
    private String outProductNo;

    @SerializedName("state")
    private ProductCouponState state;

    @SerializedName("stock")
    private StockEntity stock;

    @SerializedName("stock_bundle")
    private StockBundleEntity stockBundle;

    @SerializedName("brand_id")
    private String brandId;
}
