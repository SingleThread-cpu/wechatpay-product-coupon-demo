package com.wechatpay.java.productcoupon.client;

import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;
import com.wechatpay.java.productcoupon.model.response.CreateProductCouponResponse;
import com.wechatpay.java.utils.WXPayBrandUtility;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 服务商 - 商品券客户端
 */
public class PartnerProductCouponClient {

    private static final Logger logger = LoggerFactory.getLogger(PartnerProductCouponClient.class);

    /** 创建商品券接口地址 */
    private static final String CREATE_COUPON_API = "https://api.mch.weixin.qq.com/v3/marketing/partner/product-coupon/product-coupons";

    /** 商户号 */
    private final String mchId;
    /** 商户API证书序列号 */
    private final String certificateSerialNo;
    /** 商户API证书私钥 */
    private final PrivateKey privateKey;
    /** 微信支付公钥ID */
    private final String wechatPayPublicKeyId;
    /** 微信支付公钥 */
    private final PublicKey wechatPayPublicKey;

    /**
     * 构造服务商商品券客户端
     *
     * @param mchId                      商户号
     * @param certificateSerialNo        商户API证书序列号
     * @param privateKeyFilePath         商户API证书私钥文件路径
     * @param wechatPayPublicKeyId       微信支付公钥ID
     * @param wechatPayPublicKeyFilePath 微信支付公钥文件路径
     */
    public PartnerProductCouponClient(String mchId, String certificateSerialNo,
                                      String privateKeyFilePath, String wechatPayPublicKeyId,
                                      String wechatPayPublicKeyFilePath) {
        this.mchId = mchId;
        this.certificateSerialNo = certificateSerialNo;
        this.privateKey = WXPayBrandUtility.loadPrivateKeyFromPath(privateKeyFilePath);
        this.wechatPayPublicKeyId = wechatPayPublicKeyId;
        this.wechatPayPublicKey = WXPayBrandUtility.loadPublicKeyFromPath(wechatPayPublicKeyFilePath);
    }

    /**
     * 构造服务商商品券客户端
     *
     * @param mchId                商户号
     * @param certificateSerialNo  商户API证书序列号
     * @param privateKey           商户API证书私钥
     * @param wechatPayPublicKeyId 微信支付公钥ID
     * @param wechatPayPublicKey   微信支付公钥
     */
    public PartnerProductCouponClient(String mchId, String certificateSerialNo,
                                      PrivateKey privateKey, String wechatPayPublicKeyId,
                                      PublicKey wechatPayPublicKey) {
        this.mchId = mchId;
        this.certificateSerialNo = certificateSerialNo;
        this.privateKey = privateKey;
        this.wechatPayPublicKeyId = wechatPayPublicKeyId;
        this.wechatPayPublicKey = wechatPayPublicKey;
    }

    public CreateProductCouponResponse createCoupon(CreateProductCouponRequest request) {
        return post(CREATE_COUPON_API, request, CreateProductCouponResponse.class);
    }

    private <T> T post(String url, Object requestBody, Class<T> responseClass) {
        String method = "POST";
        String reqBody = WXPayBrandUtility.toJson(requestBody);
        String path = url.replace("https://api.mch.weixin.qq.com", "");

        String authorization = WXPayBrandUtility.buildAuthorization(
                mchId, certificateSerialNo, privateKey, method, path, reqBody);

        // 打印请求调试信息
        logger.debug("========== 请求调试信息 ==========");
        logger.debug("URL: {}", url);
        logger.debug("Path: {}", path);
        logger.debug("Method: {}", method);
        logger.debug("Mch ID: {}", mchId);
        logger.debug("Certificate Serial No: {}", certificateSerialNo);
        logger.debug("Wechatpay-Serial: {}", wechatPayPublicKeyId);
        logger.debug("Authorization: {}", authorization);
        logger.debug("Request Body: {}", reqBody);
        logger.debug("==================================");

        Request.Builder reqBuilder = new Request.Builder().url(url);
        reqBuilder.addHeader("Accept", "application/json");
        reqBuilder.addHeader("Wechatpay-Serial", wechatPayPublicKeyId);
        reqBuilder.addHeader("Authorization", authorization);
        reqBuilder.addHeader("Content-Type", "application/json");

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), reqBody);
        reqBuilder.method(method, body);

        OkHttpClient client = new OkHttpClient.Builder().build();
        try (Response httpResponse = client.newCall(reqBuilder.build()).execute()) {
            String respBody = WXPayBrandUtility.extractBody(httpResponse);
            String requestId = httpResponse.header("Request-ID");
            String signatureType = httpResponse.header("Wechatpay-Signature-Type");

            // 打印响应调试信息
            logger.debug("========== 响应调试信息 ==========");
            logger.debug("Status Code: {}", httpResponse.code());
            logger.debug("Request-ID: {}", requestId);
            logger.debug("Wechatpay-Signature-Type: {}", signatureType);
            logger.debug("Response Body: {}", respBody);
            logger.debug("==================================");

            if (httpResponse.code() >= 200 && httpResponse.code() < 300) {
                WXPayBrandUtility.validateResponse(
                        wechatPayPublicKeyId, wechatPayPublicKey,
                        httpResponse.headers(), respBody);
                return WXPayBrandUtility.fromJson(respBody, responseClass);
            } else {
                throw new WXPayBrandUtility.ApiException(
                        httpResponse.code(), respBody, httpResponse.headers());
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Sending request to " + path + " failed.", e);
        }
    }
}
