package com.wechatpay.java.productcoupon.branddemo.many;

import com.wechatpay.java.productcoupon.model.entity.*;
import com.wechatpay.java.productcoupon.model.enums.*;
import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 品牌-多次优惠-单品券-兑换券
 * 
 * usage_mode: PROGRESSIVE_BUNDLE（多次优惠）
 * scope: SINGLE（单品券）
 * type: EXCHANGE（兑换券）
 */
public class SingleExchangeSequential {

  /**
   * 构建多次优惠-单品-兑换券请求
   * 
   * @return 创建商品券请求对象
   */
  public static CreateProductCouponRequest buildRequest() {
    CreateProductCouponRequest request = new CreateProductCouponRequest();
    // 必填，创建请求单号，6-40个字符，品牌侧需保持唯一性
    request.setOutRequestNo("MANY_SINGLE_EXCHANGE_20250101_001");
    // 必填，品牌ID，由微信支付分配
    request.setBrandId("120344");
    // 选填，商户侧商品券唯一标识
    request.setOutProductNo("Product_1234567894");
    // 必填，优惠范围：SINGLE-单品券(兑换券仅支持单品券)
    request.setScope(ProductCouponScope.SINGLE);
    // 必填，商品券类型：NORMAL-满减券, DISCOUNT-折扣券, EXCHANGE-兑换券(仅单品券)
    request.setType(ProductCouponType.EXCHANGE);
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
    displayInfo.setName("9.9元兑换商品(可用3次)");
    // 必填，商品券图片URL
    displayInfo.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，背景图URL
    displayInfo.setBackgroundUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，详情图URL列表，最多6张
    displayInfo.setDetailImageUrlList(new ArrayList<>(Arrays.asList("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx")));
    // 条件必填(单品券)，商品原价，单位：分
    displayInfo.setOriginalPrice(3200L);
    // 条件必填(单品券)，套餐组合列表
    ComboPackage combo = new ComboPackage();
    combo.setName("超值兑换套餐");
    combo.setPickCount(3L);
    
    ComboPackageChoice choice1 = new ComboPackageChoice();
    choice1.setName("大汉堡");
    choice1.setPrice(1500L);
    choice1.setCount(1L);
    choice1.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/burger");
    
    ComboPackageChoice choice2 = new ComboPackageChoice();
    choice2.setName("香辣鸡翅");
    choice2.setPrice(1200L);
    choice2.setCount(1L);
    choice2.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/wings");
    
    ComboPackageChoice choice3 = new ComboPackageChoice();
    choice3.setName("大可乐");
    choice3.setPrice(500L);
    choice3.setCount(1L);
    choice3.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/cola");
    
    combo.setChoiceList(new ArrayList<>(Arrays.asList(choice1, choice2, choice3)));
    displayInfo.setComboPackageList(new ArrayList<>(Arrays.asList(combo)));
    request.setDisplayInfo(displayInfo);

    // 条件必填，批次信息(当usage_mode=PROGRESSIVE_BUNDLE时必填)
    request.setStockBundle(createStock("单品兑换批次", 0, 990));

    return request;
  }

  /**
   * 创建兑换券批次
   */
  private static StockForProgressiveBundle createStock(String remark, long threshold, long exchangePrice) {
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

    // 兑换券规则 - 多次优惠需要提供与次数匹配的规则列表
    ExchangeCouponUsageRule exchangeCoupon1 = new ExchangeCouponUsageRule();
    exchangeCoupon1.setThreshold(threshold);
    exchangeCoupon1.setExchangePrice(exchangePrice);
    
    ExchangeCouponUsageRule exchangeCoupon2 = new ExchangeCouponUsageRule();
    exchangeCoupon2.setThreshold(threshold);
    exchangeCoupon2.setExchangePrice(exchangePrice);
    
    ExchangeCouponUsageRule exchangeCoupon3 = new ExchangeCouponUsageRule();
    exchangeCoupon3.setThreshold(threshold);
    exchangeCoupon3.setExchangePrice(exchangePrice);
    
    progressiveBundleUsageRule.setExchangeCouponList(new ArrayList<>(Arrays.asList(
        exchangeCoupon1, exchangeCoupon2, exchangeCoupon3)));
    stock.setProgressiveBundleUsageRule(progressiveBundleUsageRule);

    UsageRuleDisplayInfo usageRuleDisplayInfo = new UsageRuleDisplayInfo();
    usageRuleDisplayInfo.setCouponUsageMethodList(new ArrayList<>(Arrays.asList(
        CouponUsageMethod.OFFLINE, CouponUsageMethod.MINI_PROGRAM, CouponUsageMethod.PAYMENT_CODE)));
    usageRuleDisplayInfo.setMiniProgramAppid("wxaf5ec0723f382d71");
    usageRuleDisplayInfo.setMiniProgramPath("/pages/index/product");
    usageRuleDisplayInfo.setUsageDescription("9.9元兑换原价99元商品");
    
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
    entranceMiniProgram.setEntranceWording("立即兑换");
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
