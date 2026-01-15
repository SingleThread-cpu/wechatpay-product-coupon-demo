package com.wechatpay.java.productcoupon.partnerdemo;

import com.wechatpay.java.productcoupon.client.PartnerProductCouponClient;
import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;
import com.wechatpay.java.productcoupon.model.response.CreateProductCouponResponse;
import com.wechatpay.java.productcoupon.partnerdemo.many.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务商商品券创建示例
 * 
 * 包含10种券类型：
 * - 多次优惠(PROGRESSIVE_BUNDLE): 全场折扣、全场满减、单品折扣、单品满减、单品兑换
 * - 单券(SINGLE): 全场折扣、全场满减、单品折扣、单品满减、单品兑换
 */
public class PartnerProductCouponDemo {

    private static final Logger logger = LoggerFactory.getLogger(PartnerProductCouponDemo.class);

    // ============ 服务商配置 ============
    // TODO: 请准备服务商开发必要参数，参考：https://pay.weixin.qq.com/doc/v3/partner/4013080340
    
    /** 服务商商户号，由微信支付分配 */
    private static final String MCH_ID = "YOUR_MCH_ID";
    
    /** 服务商API证书序列号，如何获取请参考 https://pay.weixin.qq.com/doc/v3/partner/4013058924 */
    private static final String CERTIFICATE_SERIAL_NO = "1DDE55AD98Exxxxxxxxxx";
    
    /** 服务商API证书私钥文件路径，本地文件路径 */
    private static final String PRIVATE_KEY_PATH = "/path/to/apiclient_key.pem";
    
    /** 微信支付公钥ID，如何获取请参考 https://pay.weixin.qq.com/doc/v3/partner/4013038589 */
    private static final String WECHAT_PAY_PUBLIC_KEY_ID = "PUB_KEY_ID_xxxxxxxxxxxxx";
    
    /** 微信支付公钥文件路径，本地文件路径 */
    private static final String WECHAT_PAY_PUBLIC_KEY_PATH = "/path/to/wxp_pub.pem";

    public static void main(String[] args) {
        // 1. 创建客户端（使用服务商商户号进行签名认证）
        PartnerProductCouponClient client = new PartnerProductCouponClient(
                MCH_ID,
                CERTIFICATE_SERIAL_NO,
                PRIVATE_KEY_PATH,
                WECHAT_PAY_PUBLIC_KEY_ID,
                WECHAT_PAY_PUBLIC_KEY_PATH
        );

        // 2. 选择券类型并构建请求（以下10种选其一）
        CreateProductCouponRequest request;

        request = AllDiscountMany.buildRequest();      // 多次优惠-全场-折扣券
        // request = AllNormalMany.buildRequest();     // 多次优惠-全场-满减券
        // request = SingleDiscountMany.buildRequest();// 多次优惠-单品-折扣券
        // request = SingleNormalMany.buildRequest();  // 多次优惠-单品-满减券
        // request = SingleExchangeMany.buildRequest();// 多次优惠-单品-兑换券
        // request = AllDiscountSingle.buildRequest();   // 单券-全场-折扣券
        // request = AllNormalSingle.buildRequest();     // 单券-全场-满减券
        // request = SingleDiscountSingle.buildRequest();// 单券-单品-折扣券
        // request = SingleNormalSingle.buildRequest();  // 单券-单品-满减券
        // request = SingleExchangeSingle.buildRequest();// 单券-单品-兑换券

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
