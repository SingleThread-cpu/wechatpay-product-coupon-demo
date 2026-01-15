package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 小程序入口
 */
@Data
public class EntranceMiniProgram {
    @SerializedName("appid")
    private String appid;

    @SerializedName("path")
    private String path;

    @SerializedName("entrance_wording")
    private String entranceWording;

    @SerializedName("guidance_wording")
    private String guidanceWording;
}
