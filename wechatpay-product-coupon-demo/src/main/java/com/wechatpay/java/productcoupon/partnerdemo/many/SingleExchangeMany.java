package com.wechatpay.java.productcoupon.partnerdemo.many;

import com.wechatpay.java.productcoupon.model.entity.*;
import com.wechatpay.java.productcoupon.model.enums.*;
import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;

import java.util.ArrayList;

/**
 * 服务商-多次优惠-单品券-兑换券
 */
public class SingleExchangeMany {

  public static CreateProductCouponRequest buildRequest() {
    CreateProductCouponRequest request = new CreateProductCouponRequest();
    request.setOutRequestNo("MANY_SINGLE_EXCHANGE_20250101_005");
    request.setBrandId("120344");
    request.setOutProductNo("Product_1234567890");
    request.setScope(ProductCouponScope.SINGLE);
    request.setType(ProductCouponType.EXCHANGE);
    request.setUsageMode(UsageMode.PROGRESSIVE_BUNDLE);

    ProgressiveBundleUsageInfo progressiveBundleUsageInfo = new ProgressiveBundleUsageInfo();
    progressiveBundleUsageInfo.setCount(3L);
    progressiveBundleUsageInfo.setIntervalDays(7L);
    request.setProgressiveBundleUsageInfo(progressiveBundleUsageInfo);

    ProductCouponDisplayInfo displayInfo = new ProductCouponDisplayInfo();
    displayInfo.setName("9.9元兑换原价99商品(可用3次)");
    displayInfo.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    displayInfo.setBackgroundUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    ArrayList<String> detailImageUrlList = new ArrayList<>();
    detailImageUrlList.add("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    displayInfo.setDetailImageUrlList(detailImageUrlList);
    displayInfo.setOriginalPrice(3200L);

    ArrayList<ComboPackage> comboPackageList = new ArrayList<>();
    ComboPackage combo = new ComboPackage();
    combo.setName("超值兑换套餐");
    combo.setPickCount(3L);
    ArrayList<ComboPackageChoice> choiceList = new ArrayList<>();
    ComboPackageChoice choice1 = new ComboPackageChoice();
    choice1.setName("大汉堡");
    choice1.setPrice(1500L);
    choice1.setCount(1L);
    choice1.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/burger");
    choiceList.add(choice1);
    ComboPackageChoice choice2 = new ComboPackageChoice();
    choice2.setName("香辣鸡翅");
    choice2.setPrice(1200L);
    choice2.setCount(1L);
    choice2.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/wings");
    choiceList.add(choice2);
    ComboPackageChoice choice3 = new ComboPackageChoice();
    choice3.setName("大可乐");
    choice3.setPrice(500L);
    choice3.setCount(1L);
    choice3.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/cola");
    choiceList.add(choice3);
    combo.setChoiceList(choiceList);
    comboPackageList.add(combo);
    displayInfo.setComboPackageList(comboPackageList);
    request.setDisplayInfo(displayInfo);

    request.setStockBundle(createStock("9.9元兑换批次", 0, 990));
    return request;
  }

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
    ArrayList<WeekEnum> dayList = new ArrayList<>();
    dayList.add(WeekEnum.MONDAY);
    dayList.add(WeekEnum.TUESDAY);
    dayList.add(WeekEnum.WEDNESDAY);
    dayList.add(WeekEnum.THURSDAY);
    dayList.add(WeekEnum.FRIDAY);
    dayList.add(WeekEnum.SATURDAY);
    dayList.add(WeekEnum.SUNDAY);
    weeklyAvailablePeriod.setDayList(dayList);
    couponAvailablePeriod.setWeeklyAvailablePeriod(weeklyAvailablePeriod);
    progressiveBundleUsageRule.setCouponAvailablePeriod(couponAvailablePeriod);

    ArrayList<ExchangeCouponUsageRule> exchangeCouponList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      ExchangeCouponUsageRule exchangeCoupon = new ExchangeCouponUsageRule();
      exchangeCoupon.setThreshold(threshold);
      exchangeCoupon.setExchangePrice(exchangePrice);
      exchangeCouponList.add(exchangeCoupon);
    }
    progressiveBundleUsageRule.setExchangeCouponList(exchangeCouponList);
    stock.setProgressiveBundleUsageRule(progressiveBundleUsageRule);

    UsageRuleDisplayInfo usageRuleDisplayInfo = new UsageRuleDisplayInfo();
    ArrayList<CouponUsageMethod> couponUsageMethodList = new ArrayList<>();
    couponUsageMethodList.add(CouponUsageMethod.OFFLINE);
    couponUsageMethodList.add(CouponUsageMethod.MINI_PROGRAM);
    couponUsageMethodList.add(CouponUsageMethod.PAYMENT_CODE);
    usageRuleDisplayInfo.setCouponUsageMethodList(couponUsageMethodList);
    usageRuleDisplayInfo.setMiniProgramAppid("wxaf5ec0723f382d71");
    usageRuleDisplayInfo.setMiniProgramPath("/pages/index/product");
    usageRuleDisplayInfo.setUsageDescription("用9.9元兑换原价99元商品");
    CouponAvailableStoreInfo couponAvailableStoreInfo = new CouponAvailableStoreInfo();
    couponAvailableStoreInfo.setDescription("所有门店可用");
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
