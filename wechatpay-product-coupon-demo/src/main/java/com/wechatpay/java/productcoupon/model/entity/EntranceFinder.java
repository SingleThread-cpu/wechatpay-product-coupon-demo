package com.wechatpay.java.productcoupon.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 视频号入口
 */
@Data
public class EntranceFinder {
    @SerializedName("finder_id")
    private String finderId;

    @SerializedName("finder_video_id")
    private String finderVideoId;

    @SerializedName("finder_video_cover_image_url")
    private String finderVideoCoverImageUrl;
}
