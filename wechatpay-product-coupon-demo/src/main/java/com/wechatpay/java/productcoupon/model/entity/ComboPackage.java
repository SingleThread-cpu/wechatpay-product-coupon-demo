package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 套餐组合
 */
@Data
public class ComboPackage {
    @SerializedName("name")
    private String name;

    @SerializedName("pick_count")
    private Long pickCount;

    @SerializedName("choice_list")
    private List<ComboPackageChoice> choiceList = new ArrayList<>();
}
