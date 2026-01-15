package com.wechatpay.java.productcoupon.branddemo.many;

import com.wechatpay.java.productcoupon.model.entity.*;
import com.wechatpay.java.productcoupon.model.enums.*;
import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 品牌-多次优惠-全场券-满减券
 * 
 * usage_mode: PROGRESSIVE_BUNDLE（多次优惠）
 * scope: ALL（全场券）
 * type: NORMAL（满减券）
 */
public class AllNormalSequential {

  /**
   * 构建多次优惠-全场-满减券请求
   * 
   * @return 创建商品券请求对象
   */
  public static CreateProductCouponRequest buildRequest() {
    CreateProductCouponRequest request = new CreateProductCouponRequest();
    // 必填，创建请求单号，6-40个字符，品牌侧需保持唯一性
    request.setOutRequestNo("MANY_ALL_NORMAL_20250101_001");
    // 必填，品牌ID，由微信支付分配
    request.setBrandId("120344");
    // 选填，商户侧商品券唯一标识
    request.setOutProductNo("Product_1234567891");
    // 必填，优惠范围：ALL-全场券(仅支持NORMAL/DISCOUNT), SINGLE-单品券(支持NORMAL/DISCOUNT/EXCHANGE)
    request.setScope(ProductCouponScope.ALL);
    // 必填，商品券类型：NORMAL-满减券, DISCOUNT-折扣券, EXCHANGE-兑换券(仅单品券)
    request.setType(ProductCouponType.NORMAL);
    // 必填，使用模式：SINGLE-单券, PROGRESSIVE_BUNDLE-多次优惠
    request.setUsageMode(UsageMode.PROGRESSIVE_BUNDLE);

    // 条件必填，多次优惠模式配置信息(当usage_mode=PROGRESSIVE_BUNDLE时必填)
    ProgressiveBundleUsageInfo progressiveBundleUsageInfo = new ProgressiveBundleUsageInfo();
    // 必填，可使用次数，最少3次，最多15次
    progressiveBundleUsageInfo.setCount(3L);
    // 选填，多次优惠使用间隔天数，0表示不限制，最高7天，默认0
    progressiveBundleUsageInfo.setIntervalDays(0L);
    request.setProgressiveBundleUsageInfo(progressiveBundleUsageInfo);

    // 必填，商品券展示信息
    ProductCouponDisplayInfo displayInfo = new ProductCouponDisplayInfo();
    // 必填，商品券名称，最多12个字符
    displayInfo.setName("全场满100减20(可用3次)");
    // 必填，商品券图片URL
    displayInfo.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，背景图URL
    displayInfo.setBackgroundUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，详情图URL列表，最多6张
    displayInfo.setDetailImageUrlList(new ArrayList<>(Arrays.asList("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx")));
    request.setDisplayInfo(displayInfo);

    // 条件必填，批次信息(当usage_mode=PROGRESSIVE_BUNDLE时必填)
    request.setStockBundle(createStock("满减批次", 10000, 2000));

    return request;
  }

  /**
   * 创建满减券批次
   */
  private static StockForProgressiveBundle createStock(String remark, long threshold, long discountAmount) {
    StockForProgressiveBundle stock = new StockForProgressiveBundle();
    stock.setRemark(remark);
    stock.setCouponCodeMode(CouponCodeMode.WECHATPAY);

    StockSendRule stockSendRule = new StockSendRule();
    stockSendRule.setMaxCount(10000000L);
    stockSendRule.setMaxCountPerUser(1L);
    stock.setStockSendRule(stockSendRule);

    ProgressiveBundleUsageRule progressiveBundleUsageRule = new ProgressiveBundleUsageRule();
    CouponAvailablePeriod couponAvailablePeriod = new CouponAvailablePeriod();
    couponAvailablePeriod.setAvailableBeginTime("2025-12-15T00:00:00+08:00");
    couponAvailablePeriod.setAvailableEndTime("2026-03-31T23:59:59+08:00");
    couponAvailablePeriod.setAvailableDays(30L);
    
    FixedWeekPeriod weeklyAvailablePeriod = new FixedWeekPeriod();
    weeklyAvailablePeriod.setDayList(new ArrayList<>(Arrays.asList(
        WeekEnum.MONDAY, WeekEnum.TUESDAY, WeekEnum.WEDNESDAY, 
        WeekEnum.THURSDAY, WeekEnum.FRIDAY)));
    couponAvailablePeriod.setWeeklyAvailablePeriod(weeklyAvailablePeriod);
    progressiveBundleUsageRule.setCouponAvailablePeriod(couponAvailablePeriod);

    // 满减券规则 - 多次优惠需要提供与次数匹配的规则列表
    NormalCouponUsageRule normalCoupon1 = new NormalCouponUsageRule();
    normalCoupon1.setThreshold(threshold);
    normalCoupon1.setDiscountAmount(discountAmount);
    
    NormalCouponUsageRule normalCoupon2 = new NormalCouponUsageRule();
    normalCoupon2.setThreshold(threshold);
    normalCoupon2.setDiscountAmount(discountAmount);
    
    NormalCouponUsageRule normalCoupon3 = new NormalCouponUsageRule();
    normalCoupon3.setThreshold(threshold);
    normalCoupon3.setDiscountAmount(discountAmount);
    
    progressiveBundleUsageRule.setNormalCouponList(new ArrayList<>(Arrays.asList(
        normalCoupon1, normalCoupon2, normalCoupon3)));
    stock.setProgressiveBundleUsageRule(progressiveBundleUsageRule);

    UsageRuleDisplayInfo usageRuleDisplayInfo = new UsageRuleDisplayInfo();
    usageRuleDisplayInfo.setCouponUsageMethodList(new ArrayList<>(Arrays.asList(
        CouponUsageMethod.OFFLINE, CouponUsageMethod.MINI_PROGRAM, CouponUsageMethod.PAYMENT_CODE)));
    usageRuleDisplayInfo.setMiniProgramAppid("wxaf5ec0723f382d71");
    usageRuleDisplayInfo.setMiniProgramPath("/pages/index/product");
    usageRuleDisplayInfo.setUsageDescription("工作日可用");
    
    CouponAvailableStoreInfo couponAvailableStoreInfo = new CouponAvailableStoreInfo();
    couponAvailableStoreInfo.setDescription("所有门店可用，可使用小程序查看门店列表");
    couponAvailableStoreInfo.setMiniProgramAppid("wxaf5ec0723f382d71");
    couponAvailableStoreInfo.setMiniProgramPath("/pages/index/store-list");
    usageRuleDisplayInfo.setCouponAvailableStoreInfo(couponAvailableStoreInfo);
    stock.setUsageRuleDisplayInfo(usageRuleDisplayInfo);

    CouponDisplayInfo couponDisplayInfo = new CouponDisplayInfo();
    couponDisplayInfo.setCodeDisplayMode(CouponCodeDisplayMode.QRCODE);
    couponDisplayInfo.setBackgroundColor("Color010");
    
    EntranceMiniProgram entranceMiniProgram = new EntranceMiniProgram();
    entranceMiniProgram.setAppid("wxaf5ec0723f382d71");
    entranceMiniProgram.setPath("/pages/index/product");
    entranceMiniProgram.setEntranceWording("欢迎选购");
    entranceMiniProgram.setGuidanceWording("获取更多优惠");
    couponDisplayInfo.setEntranceMiniProgram(entranceMiniProgram);
    
    EntranceOfficialAccount entranceOfficialAccount = new EntranceOfficialAccount();
    entranceOfficialAccount.setAppid("wxaf5ec0723f382d71");
    couponDisplayInfo.setEntranceOfficialAccount(entranceOfficialAccount);
    
    EntranceFinder entranceFinder = new EntranceFinder();
    entranceFinder.setFinderId("gh_12345678");
    entranceFinder.setFinderVideoId("UDFsdf24df34dD456Hdf34");
    entranceFinder.setFinderVideoCoverImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    couponDisplayInfo.setEntranceFinder(entranceFinder);
    stock.setCouponDisplayInfo(couponDisplayInfo);

    stock.setStoreScope(StockStoreScope.NONE);
    
    NotifyConfig notifyConfig = new NotifyConfig();
    notifyConfig.setNotifyAppid("wxaf5ec0723f382d71");
    stock.setNotifyConfig(notifyConfig);

    return stock;
  }
}
