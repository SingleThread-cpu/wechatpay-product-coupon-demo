package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 事件通知配置
 */
@Data
public class NotifyConfig {
    @SerializedName("notify_appid")
    private String notifyAppid;
}
