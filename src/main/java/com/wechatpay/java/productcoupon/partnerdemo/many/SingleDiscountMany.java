package com.wechatpay.java.productcoupon.partnerdemo.many;

import com.wechatpay.java.productcoupon.model.entity.*;
import com.wechatpay.java.productcoupon.model.enums.*;
import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;

import java.util.ArrayList;

/**
 * 服务商-多次优惠-单品券-折扣券
 * 
 * usage_mode: PROGRESSIVE_BUNDLE（多次优惠）
 * scope: SINGLE（单品券）
 * type: DISCOUNT（折扣券）
 */
public class SingleDiscountMany {

  /**
   * 构建多次优惠-单品-折扣券请求
   * 
   * @return 创建商品券请求对象
   */
  public static CreateProductCouponRequest buildRequest() {
    CreateProductCouponRequest request = new CreateProductCouponRequest();
    // 必填，创建请求单号，6-40个字符，品牌侧需保持唯一性
    request.setOutRequestNo("MANY_SINGLE_DISCOUNT_20250101_003");
    // 必填，品牌ID，由微信支付分配
    request.setBrandId("120344");
    // 选填，商户侧商品券唯一标识
    request.setOutProductNo("Product_1234567890");
    // 必填，优惠范围：ALL-全场券(仅支持NORMAL/DISCOUNT), SINGLE-单品券(支持NORMAL/DISCOUNT/EXCHANGE)
    request.setScope(ProductCouponScope.SINGLE);
    // 必填，商品券类型：NORMAL-满减券, DISCOUNT-折扣券, EXCHANGE-兑换券(仅单品券)
    request.setType(ProductCouponType.DISCOUNT);
    // 必填，使用模式：SINGLE-单券, PROGRESSIVE_BUNDLE-多次优惠
    request.setUsageMode(UsageMode.PROGRESSIVE_BUNDLE);

    // 条件必填，多次优惠模式配置信息(当usage_mode=PROGRESSIVE_BUNDLE时必填)
    ProgressiveBundleUsageInfo progressiveBundleUsageInfo = new ProgressiveBundleUsageInfo();
    // 必填，可使用次数，最少3次，最多15次
    progressiveBundleUsageInfo.setCount(3L);
    // 选填，多次优惠使用间隔天数，0表示不限制，最高7天，默认0
    progressiveBundleUsageInfo.setIntervalDays(0L);
    request.setProgressiveBundleUsageInfo(progressiveBundleUsageInfo);

    // 必填，商品券展示信息（单品券需要original_price或combo_package_list二选一）
    ProductCouponDisplayInfo displayInfo = new ProductCouponDisplayInfo();
    // 必填，商品券名称，最多12个字符
    displayInfo.setName("指定商品8折(可用3次)");
    // 必填，商品券图片URL
    displayInfo.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，背景图URL
    displayInfo.setBackgroundUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，详情图URL列表，最多6张
    ArrayList<String> detailImageUrlList = new ArrayList<>();
    detailImageUrlList.add("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    displayInfo.setDetailImageUrlList(detailImageUrlList);
    // 条件必填(单品券)，商品原价，单位：分
    displayInfo.setOriginalPrice(2000L);

    // 条件必填(单品券)，套餐组合列表
    ArrayList<ComboPackage> comboPackageList = new ArrayList<>();
    ComboPackage combo = new ComboPackage();
    combo.setName("超值套餐");
    combo.setPickCount(2L);
    ArrayList<ComboPackageChoice> choiceList = new ArrayList<>();
    ComboPackageChoice choice1 = new ComboPackageChoice();
    choice1.setName("大汉堡");
    choice1.setPrice(1500L);
    choice1.setCount(1L);
    choice1.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/burger");
    choice1.setMiniProgramAppid("wxaf5ec0723f382d71");
    choice1.setMiniProgramPath("/pages/index/burger");
    choiceList.add(choice1);
    ComboPackageChoice choice2 = new ComboPackageChoice();
    choice2.setName("大可乐");
    choice2.setPrice(500L);
    choice2.setCount(1L);
    choice2.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/cola");
    choice2.setMiniProgramAppid("wxaf5ec0723f382d71");
    choice2.setMiniProgramPath("/pages/index/cola");
    choiceList.add(choice2);
    combo.setChoiceList(choiceList);
    comboPackageList.add(combo);
    displayInfo.setComboPackageList(comboPackageList);
    request.setDisplayInfo(displayInfo);

    // 条件必填，批次信息(当usage_mode=PROGRESSIVE_BUNDLE时必填)
    request.setStockBundle(createStock("指定商品8折批次", 0, 20));

    return request;
  }

  /**
   * 创建折扣券批次
   */
  private static StockForProgressiveBundle createStock(String remark, long threshold, long percentOff) {
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
    weeklyAvailablePeriod.setDayList(dayList);
    couponAvailablePeriod.setWeeklyAvailablePeriod(weeklyAvailablePeriod);
    progressiveBundleUsageRule.setCouponAvailablePeriod(couponAvailablePeriod);

    // 折扣券规则 - 多次优惠需要提供与次数匹配的规则列表
    ArrayList<DiscountCouponUsageRule> discountCouponList = new ArrayList<>();
    // 第1次使用
    DiscountCouponUsageRule discountCoupon1 = new DiscountCouponUsageRule();
    discountCoupon1.setThreshold(threshold);
    discountCoupon1.setPercentOff(percentOff);
    discountCouponList.add(discountCoupon1);
    // 第2次使用
    DiscountCouponUsageRule discountCoupon2 = new DiscountCouponUsageRule();
    discountCoupon2.setThreshold(threshold);
    discountCoupon2.setPercentOff(percentOff);
    discountCouponList.add(discountCoupon2);
    // 第3次使用
    DiscountCouponUsageRule discountCoupon3 = new DiscountCouponUsageRule();
    discountCoupon3.setThreshold(threshold);
    discountCoupon3.setPercentOff(percentOff);
    discountCouponList.add(discountCoupon3);
    progressiveBundleUsageRule.setDiscountCouponList(discountCouponList);
    stock.setProgressiveBundleUsageRule(progressiveBundleUsageRule);

    UsageRuleDisplayInfo usageRuleDisplayInfo = new UsageRuleDisplayInfo();
    ArrayList<CouponUsageMethod> couponUsageMethodList = new ArrayList<>();
    couponUsageMethodList.add(CouponUsageMethod.OFFLINE);
    couponUsageMethodList.add(CouponUsageMethod.MINI_PROGRAM);
    couponUsageMethodList.add(CouponUsageMethod.PAYMENT_CODE);
    usageRuleDisplayInfo.setCouponUsageMethodList(couponUsageMethodList);
    usageRuleDisplayInfo.setMiniProgramAppid("wxaf5ec0723f382d71");
    usageRuleDisplayInfo.setMiniProgramPath("/pages/index/product");
    usageRuleDisplayInfo.setUsageDescription("指定商品可用，享8折优惠");
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
