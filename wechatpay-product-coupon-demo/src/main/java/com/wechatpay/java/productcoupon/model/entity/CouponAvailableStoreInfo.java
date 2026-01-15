package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 可用门店信息
 */
@Data
public class CouponAvailableStoreInfo {
    @SerializedName("description")
    private String description;

    @SerializedName("mini_program_appid")
    private String miniProgramAppid;

    @SerializedName("mini_program_path")
    private String miniProgramPath;
}
