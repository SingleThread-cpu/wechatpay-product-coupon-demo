package com.wechatpay.java.productcoupon.partnerdemo.single;

import com.wechatpay.java.productcoupon.model.entity.*;
import com.wechatpay.java.productcoupon.model.enums.*;
import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;

import java.util.ArrayList;

/**
 * 服务商-单券-单品券-兑换券
 * 
 * usage_mode: SINGLE（单券）
 * scope: SINGLE（单品券）
 * type: EXCHANGE（兑换券）
 * 
 * 优惠规则配置位置：stock.single_usage_rule.exchange_coupon（单品券在single_usage_rule中配置）
 */
public class SingleExchangeSingle {

  /**
   * 构建单券-单品-兑换券请求
   * 
   * @return 创建商品券请求对象
   */
  public static CreateProductCouponRequest buildRequest() {
    CreateProductCouponRequest request = new CreateProductCouponRequest();
    // 必填，创建请求单号，6-40个字符，品牌侧需保持唯一性
    request.setOutRequestNo("SINGLE_SINGLE_EXCHANGE_20250101_005");
    // 必填，品牌ID，由微信支付分配
    request.setBrandId("120344");
    // 必填，优惠范围：SINGLE-单品券(兑换券仅支持单品券)
    request.setScope(ProductCouponScope.SINGLE);
    // 必填，商品券类型：NORMAL-满减券, DISCOUNT-折扣券, EXCHANGE-兑换券(仅单品券)
    request.setType(ProductCouponType.EXCHANGE);
    // 必填，使用模式：SINGLE-单券, PROGRESSIVE_BUNDLE-多次优惠
    request.setUsageMode(UsageMode.SINGLE);
    // 选填，商户侧商品券唯一标识
    request.setOutProductNo("Product_SINGLE_005");

    // 必填，商品券展示信息
    ProductCouponDisplayInfo displayInfo = new ProductCouponDisplayInfo();
    // 必填，商品券名称，最多12个字符
    displayInfo.setName("9.9元兑换原价99元商品");
    // 必填，商品券图片URL
    displayInfo.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，背景图URL
    displayInfo.setBackgroundUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，详情图URL列表，最多6张
    ArrayList<String> detailImageUrlList = new ArrayList<>();
    detailImageUrlList.add("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    displayInfo.setDetailImageUrlList(detailImageUrlList);
    // 条件必填(单品券)，商品原价，单位：分
    displayInfo.setOriginalPrice(3200L);
    // 条件必填(单品券)，套餐组合列表
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

    // 条件必填，批次信息(当usage_mode=SINGLE时必填)
    StockForCreate stock = new StockForCreate();
    stock.setRemark("8月单品兑换批次");
    stock.setCouponCodeMode(CouponCodeMode.WECHATPAY);
    StockSendRule stockSendRule = new StockSendRule();
    stockSendRule.setMaxCount(10000000L);
    stockSendRule.setMaxCountPerDay(100000L);
    stockSendRule.setMaxCountPerUser(1L);
    stock.setStockSendRule(stockSendRule);

    // 单券使用规则（scope=SINGLE时在此配置优惠规则）
    SingleUsageRule singleUsageRule = new SingleUsageRule();
    CouponAvailablePeriod couponAvailablePeriod = new CouponAvailablePeriod();
    couponAvailablePeriod.setAvailableBeginTime("2025-12-15T00:00:00+08:00");
    couponAvailablePeriod.setAvailableEndTime("2026-03-31T23:59:59+08:00");
    couponAvailablePeriod.setAvailableDays(30L);
    couponAvailablePeriod.setWaitDaysAfterReceive(0L);
    FixedWeekPeriod weeklyAvailablePeriod = new FixedWeekPeriod();
    ArrayList<WeekEnum> dayList = new ArrayList<>();
    dayList.add(WeekEnum.MONDAY);
    dayList.add(WeekEnum.TUESDAY);
    dayList.add(WeekEnum.WEDNESDAY);
    dayList.add(WeekEnum.THURSDAY);
    dayList.add(WeekEnum.FRIDAY);
    weeklyAvailablePeriod.setDayList(dayList);
    couponAvailablePeriod.setWeeklyAvailablePeriod(weeklyAvailablePeriod);
    singleUsageRule.setCouponAvailablePeriod(couponAvailablePeriod);
    // 条件必填，兑换券使用规则(当type=EXCHANGE时在此配置)
    ExchangeCouponUsageRule exchangeCoupon = new ExchangeCouponUsageRule();
    // 必填，门槛金额(单位：分)，0表示无门槛
    exchangeCoupon.setThreshold(0L);
    // 必填，兑换价格(单位：分)，用户实际支付金额
    exchangeCoupon.setExchangePrice(990L);
    singleUsageRule.setExchangeCoupon(exchangeCoupon);
    stock.setSingleUsageRule(singleUsageRule);

    // 使用规则展示信息
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
    couponAvailableStoreInfo.setDescription("所有门店可用，可使用小程序查看门店列表");
    couponAvailableStoreInfo.setMiniProgramAppid("wxaf5ec0723f382d71");
    couponAvailableStoreInfo.setMiniProgramPath("/pages/index/store-list");
    usageRuleDisplayInfo.setCouponAvailableStoreInfo(couponAvailableStoreInfo);
    stock.setUsageRuleDisplayInfo(usageRuleDisplayInfo);

    // 用户券展示信息
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

    // 事件通知配置
    NotifyConfig notifyConfig = new NotifyConfig();
    notifyConfig.setNotifyAppid("wxaf5ec0723f382d71");
    stock.setNotifyConfig(notifyConfig);
    stock.setStoreScope(StockStoreScope.NONE);
    request.setStock(stock);

    return request;
  }
}
