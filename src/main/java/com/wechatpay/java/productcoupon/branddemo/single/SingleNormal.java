package com.wechatpay.java.productcoupon.branddemo.single;

import com.wechatpay.java.productcoupon.model.entity.*;
import com.wechatpay.java.productcoupon.model.enums.*;
import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 品牌-单券-单品券-满减券
 * 
 * usage_mode: SINGLE（单券）
 * scope: SINGLE（单品券）
 * type: NORMAL（满减券）
 * 
 * 优惠规则配置位置：stock.single_usage_rule.normal_coupon（单品券在single_usage_rule中配置）
 */
public class SingleNormal {

  /**
   * 构建单券-单品-满减券请求
   * 
   * @return 创建商品券请求对象
   */
  public static CreateProductCouponRequest buildRequest() {
    CreateProductCouponRequest request = new CreateProductCouponRequest();
    // 必填，创建请求单号，6-40个字符，品牌侧需保持唯一性
    request.setOutRequestNo("SINGLE_SINGLE_NORMAL_20250101_004");
    // 必填，品牌ID，由微信支付分配
    request.setBrandId("120344");
    // 必填，优惠范围：ALL-全场券(仅支持NORMAL/DISCOUNT), SINGLE-单品券(支持NORMAL/DISCOUNT/EXCHANGE)
    request.setScope(ProductCouponScope.SINGLE);
    // 必填，商品券类型：NORMAL-满减券, DISCOUNT-折扣券, EXCHANGE-兑换券(仅单品券)
    request.setType(ProductCouponType.NORMAL);
    // 必填，使用模式：SINGLE-单券, PROGRESSIVE_BUNDLE-多次优惠
    request.setUsageMode(UsageMode.SINGLE);
    // 选填，商户侧商品券唯一标识
    request.setOutProductNo("Product_SINGLE_004");

    // 必填，商品券展示信息
    ProductCouponDisplayInfo displayInfo = new ProductCouponDisplayInfo();
    // 必填，商品券名称，最多12个字符
    displayInfo.setName("指定商品满50减10");
    // 必填，商品券图片URL
    displayInfo.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，背景图URL
    displayInfo.setBackgroundUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，详情图URL列表，最多6张
    displayInfo.setDetailImageUrlList(new ArrayList<>(Arrays.asList("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx")));
    // 条件必填(单品券)，商品原价，单位：分
    displayInfo.setOriginalPrice(4500L);
    // 条件必填(单品券)，套餐组合列表
    ComboPackage combo = new ComboPackage();
    combo.setName("套餐A");
    combo.setPickCount(2L);
    
    ComboPackageChoice choice1 = new ComboPackageChoice();
    choice1.setName("鸡腿饭");
    choice1.setPrice(2000L);
    choice1.setCount(1L);
    choice1.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/chicken");
    
    ComboPackageChoice choice2 = new ComboPackageChoice();
    choice2.setName("牛肉饭");
    choice2.setPrice(2500L);
    choice2.setCount(1L);
    choice2.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/beef");
    
    combo.setChoiceList(new ArrayList<>(Arrays.asList(choice1, choice2)));
    displayInfo.setComboPackageList(new ArrayList<>(Arrays.asList(combo)));
    request.setDisplayInfo(displayInfo);

    // 条件必填，批次信息(当usage_mode=SINGLE时必填)
    StockForCreate stock = new StockForCreate();
    stock.setRemark("8月单品满减批次");
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
    weeklyAvailablePeriod.setDayList(new ArrayList<>(Arrays.asList(
        WeekEnum.MONDAY, WeekEnum.TUESDAY, WeekEnum.WEDNESDAY, 
        WeekEnum.THURSDAY, WeekEnum.FRIDAY)));
    couponAvailablePeriod.setWeeklyAvailablePeriod(weeklyAvailablePeriod);
    singleUsageRule.setCouponAvailablePeriod(couponAvailablePeriod);
    
    // 条件必填，满减券使用规则(当type=NORMAL时在此配置)
    NormalCouponUsageRule normalCoupon = new NormalCouponUsageRule();
    // 必填，门槛金额(单位：分)，0表示无门槛
    normalCoupon.setThreshold(5000L);
    // 必填，优惠金额(单位：分)
    normalCoupon.setDiscountAmount(1000L);
    singleUsageRule.setNormalCoupon(normalCoupon);
    stock.setSingleUsageRule(singleUsageRule);

    // 使用规则展示信息
    UsageRuleDisplayInfo usageRuleDisplayInfo = new UsageRuleDisplayInfo();
    usageRuleDisplayInfo.setCouponUsageMethodList(new ArrayList<>(Arrays.asList(
        CouponUsageMethod.OFFLINE, CouponUsageMethod.MINI_PROGRAM, CouponUsageMethod.PAYMENT_CODE)));
    usageRuleDisplayInfo.setMiniProgramAppid("wxaf5ec0723f382d71");
    usageRuleDisplayInfo.setMiniProgramPath("/pages/index/product");
    usageRuleDisplayInfo.setUsageDescription("指定商品满50减10");
    
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

    // 事件通知配置
    NotifyConfig notifyConfig = new NotifyConfig();
    notifyConfig.setNotifyAppid("wxaf5ec0723f382d71");
    stock.setNotifyConfig(notifyConfig);
    stock.setStoreScope(StockStoreScope.NONE);
    
    request.setStock(stock);

    return request;
  }
}
