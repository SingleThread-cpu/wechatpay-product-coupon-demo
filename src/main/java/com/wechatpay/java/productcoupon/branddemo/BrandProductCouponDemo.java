package com.wechatpay.java.productcoupon.branddemo;

import com.wechatpay.java.productcoupon.branddemo.single.*;
import com.wechatpay.java.productcoupon.client.BrandProductCouponClient;
import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;
import com.wechatpay.java.productcoupon.model.response.CreateProductCouponResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 品牌商品券创建示例
 * 
 * 包含10种券类型：
 * - 多次优惠(PROGRESSIVE_BUNDLE): 全场折扣、全场满减、单品折扣、单品满减、单品兑换
 * - 单券(SINGLE): 全场折扣、全场满减、单品折扣、单品满减、单品兑换
 */
public class BrandProductCouponDemo {

    private static final Logger logger = LoggerFactory.getLogger(BrandProductCouponDemo.class);

    // ============ 品牌配置 ============
    // TODO: 请准备品牌开发必要参数，参考：https://pay.weixin.qq.com/doc/brand/4015415289

    /** 品牌ID，由微信支付分配 */
    private static final String BRAND_ID = "YOUR_BRAND_ID";

    /** 品牌API证书序列号 */
    private static final String CERTIFICATE_SERIAL_NO = "YOUR_CERTIFICATE_SERIAL_NO";

    /** 品牌API证书私钥文件路径，本地文件路径 */
    private static final String PRIVATE_KEY_PATH = "/path/to/apiclient_key.pem";

    /** 微信支付公钥ID */
    private static final String WECHAT_PAY_PUBLIC_KEY_ID = "YOUR_WECHAT_PAY_PUBLIC_KEY_ID";

    /** 微信支付公钥文件路径，本地文件路径 */
    private static final String WECHAT_PAY_PUBLIC_KEY_PATH = "/path/to/pub_key.pem";

    public static void main(String[] args) {
        // 1. 创建客户端
        BrandProductCouponClient client = new BrandProductCouponClient(
                BRAND_ID,
                CERTIFICATE_SERIAL_NO,
                PRIVATE_KEY_PATH,
                WECHAT_PAY_PUBLIC_KEY_ID,
                WECHAT_PAY_PUBLIC_KEY_PATH
        );

        // 2. 选择券类型并构建请求（以下10种选其一）
        CreateProductCouponRequest request;

        // === 多次优惠（PROGRESSIVE_BUNDLE）===
        // request = AllDiscountSequential.buildRequest();   // 全场折扣券 DONE
        // request = AllNormalSequential.buildRequest();     // 全场满减券 DONE
        // request = SingleDiscountSequential.buildRequest();// 单品折扣券 DONE
       // request = SingleNormalSequential.buildRequest();     // 单品满减券
        // request = SingleExchangeSequential.buildRequest();// 单品兑换券

        // === 单券（SINGLE）===
        // request = AllDiscount.buildRequest();   // 全场折扣券
        // request = AllNormal.buildRequest();     // 全场满减券
        // request = SingleDiscount.buildRequest();// 单品折扣券
        // request = SingleNormal.buildRequest();  // 单品满减券
         request = SingleExchange.buildRequest();// 单品兑换券

        // 3. 发送请求
        try {
            CreateProductCouponResponse response = client.createCoupon(request);
            logger.info("创建成功！");
            logger.info("商品券ID: {}", response.getProductCouponId());
            logger.info("状态: {}", response.getState());
        } catch (Exception e) {
            logger.error("创建失败: {}", e.getMessage(), e);
        }
    }
}
