package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 套餐可选商品
 */
@Data
public class ComboPackageChoice {
    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Long price;

    @SerializedName("count")
    private Long count;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("mini_program_appid")
    private String miniProgramAppid;

    @SerializedName("mini_program_path")
    private String miniProgramPath;
}
