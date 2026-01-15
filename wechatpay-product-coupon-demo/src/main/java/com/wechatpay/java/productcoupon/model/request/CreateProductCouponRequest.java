package com.wechatpay.java.productcoupon.model.request;

import com.google.gson.annotations.SerializedName;
import com.wechatpay.java.productcoupon.model.entity.*;
import com.wechatpay.java.productcoupon.model.enums.ProductCouponScope;
import com.wechatpay.java.productcoupon.model.enums.ProductCouponType;
import com.wechatpay.java.productcoupon.model.enums.UsageMode;
import lombok.Data;

/**
 * 创建商品券请求
 */
@Data
public class CreateProductCouponRequest {
    @SerializedName("out_request_no")
    private String outRequestNo;

    /** 品牌ID，服务商模式下必填，品牌模式下不需要 */
    @SerializedName("brand_id")
    private String brandId;

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

    @SerializedName("stock")
    private StockForCreate stock;

    @SerializedName("stock_bundle")
    private StockForProgressiveBundle stockBundle;
}
