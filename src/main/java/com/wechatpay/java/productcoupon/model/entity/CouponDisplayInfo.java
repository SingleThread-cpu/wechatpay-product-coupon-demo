package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import com.wechatpay.java.productcoupon.model.enums.CouponCodeDisplayMode;
import lombok.Data;

/**
 * 用户券展示信息
 */
@Data
public class CouponDisplayInfo {
    @SerializedName("code_display_mode")
    private CouponCodeDisplayMode codeDisplayMode;

    @SerializedName("background_color")
    private String backgroundColor;

    @SerializedName("entrance_mini_program")
    private EntranceMiniProgram entranceMiniProgram;

    @SerializedName("entrance_official_account")
    private EntranceOfficialAccount entranceOfficialAccount;

    @SerializedName("entrance_finder")
    private EntranceFinder entranceFinder;
}
