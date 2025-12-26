package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 公众号入口
 */
@Data
public class EntranceOfficialAccount {
    @SerializedName("appid")
    private String appid;
}
