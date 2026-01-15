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
 * 品牌商户 - 商品券客户端
 */
public class BrandProductCouponClient {

    private static final Logger logger = LoggerFactory.getLogger(BrandProductCouponClient.class);

    /** 创建商品券接口地址 */
    private static final String CREATE_COUPON_API = "https://api.mch.weixin.qq.com/brand/marketing/product-coupon/product-coupons";

    private final String brandId;
    private final String certificateSerialNo;
    private final PrivateKey privateKey;
    private final String wechatPayPublicKeyId;
    private final PublicKey wechatPayPublicKey;

    public BrandProductCouponClient(String brandId, String certificateSerialNo,
                                    String privateKeyFilePath, String wechatPayPublicKeyId,
                                    String wechatPayPublicKeyFilePath) {
        this.brandId = brandId;
        this.certificateSerialNo = certificateSerialNo;
        this.privateKey = WXPayBrandUtility.loadPrivateKeyFromPath(privateKeyFilePath);
        this.wechatPayPublicKeyId = wechatPayPublicKeyId;
        this.wechatPayPublicKey = WXPayBrandUtility.loadPublicKeyFromPath(wechatPayPublicKeyFilePath);
    }



    public CreateProductCouponResponse createCoupon(CreateProductCouponRequest request) {
        return post(CREATE_COUPON_API, request, CreateProductCouponResponse.class);
    }

    private <T> T post(String url, Object requestBody, Class<T> responseClass) {
        String method = "POST";
        String reqBody = WXPayBrandUtility.toJson(requestBody);
        String path = url.replace("https://api.mch.weixin.qq.com", "");

        String authorization = WXPayBrandUtility.buildAuthorization(
                brandId, certificateSerialNo, privateKey, method, path, reqBody);

        // 打印请求调试信息
        logger.debug("========== 请求调试信息 ==========");
        logger.debug("URL: {}", url);
        logger.debug("Path: {}", path);
        logger.debug("Method: {}", method);
        logger.debug("Brand ID: {}", brandId);
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
