package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 商品券展示信息
 */
@Data
public class ProductCouponDisplayInfo {
    @SerializedName("name")
    private String name;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("background_url")
    private String backgroundUrl;

    @SerializedName("detail_image_url_list")
    private List<String> detailImageUrlList;

    @SerializedName("original_price")
    private Long originalPrice;

    @SerializedName("combo_package_list")
    private List<ComboPackage> comboPackageList;
}
