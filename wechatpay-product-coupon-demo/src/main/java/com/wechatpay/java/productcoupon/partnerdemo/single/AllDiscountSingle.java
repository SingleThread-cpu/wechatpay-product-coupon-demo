package com.wechatpay.java.productcoupon.partnerdemo.single;

import com.wechatpay.java.productcoupon.model.entity.*;
import com.wechatpay.java.productcoupon.model.enums.*;
import com.wechatpay.java.productcoupon.model.request.CreateProductCouponRequest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 服务商-单券-全场券-折扣券
 * 
 * usage_mode: SINGLE（单券）
 * scope: ALL（全场券）
 * type: DISCOUNT（折扣券）
 * 
 * 优惠规则配置位置：single_usage_info.discount_coupon（全场券在single_usage_info中配置）
 */
public class AllDiscountSingle {

  /**
   * 构建单券-全场-折扣券请求
   * 
   * @return 创建商品券请求对象
   */
  public static CreateProductCouponRequest buildRequest() {
    CreateProductCouponRequest request = new CreateProductCouponRequest();
    // 必填，创建请求单号，6-40个字符，品牌侧需保持唯一性
    request.setOutRequestNo("SINGLE_ALL_DISCOUNT_20250101_001");
    // 必填，品牌ID，由微信支付分配
    request.setBrandId("120344");
    // 必填，优惠范围：ALL-全场券(仅支持NORMAL/DISCOUNT), SINGLE-单品券(支持NORMAL/DISCOUNT/EXCHANGE)
    request.setScope(ProductCouponScope.ALL);
    // 必填，商品券类型：NORMAL-满减券, DISCOUNT-折扣券, EXCHANGE-兑换券(仅单品券)
    request.setType(ProductCouponType.DISCOUNT);
    // 必填，使用模式：SINGLE-单券, PROGRESSIVE_BUNDLE-多次优惠
    request.setUsageMode(UsageMode.SINGLE);
    // 选填，商户侧商品券唯一标识
    request.setOutProductNo("Product_SINGLE_001");

    // 条件必填，单券模式优惠信息(当usage_mode=SINGLE且scope=ALL时选填，配置优惠规则)
    SingleUsageInfo singleUsageInfo = new SingleUsageInfo();
    // 条件必填，折扣券使用规则(当type=DISCOUNT且scope=ALL时必填)
    DiscountCouponUsageRule discountCoupon = new DiscountCouponUsageRule();
    // 必填，门槛金额(单位：分)，0表示无门槛
    discountCoupon.setThreshold(10000L);
    // 必填，折扣百分比，20表示减免20%即打8折
    discountCoupon.setPercentOff(20L);
    singleUsageInfo.setDiscountCoupon(discountCoupon);
    request.setSingleUsageInfo(singleUsageInfo);

    // 必填，商品券展示信息
    ProductCouponDisplayInfo displayInfo = new ProductCouponDisplayInfo();
    // 必填，商品券名称，最多12个字符
    displayInfo.setName("全场满100立打8折");
    // 必填，商品券图片URL
    displayInfo.setImageUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，背景图URL
    displayInfo.setBackgroundUrl("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx");
    // 选填，详情图URL列表，最多6张
    displayInfo.setDetailImageUrlList(new ArrayList<>(Arrays.asList("https://wxpaylogo.qpic.cn/wxpaylogo/xxxxx/xxx")));
    request.setDisplayInfo(displayInfo);

    // 条件必填，批次信息(当usage_mode=SINGLE时必填)
    StockForCreate stock = new StockForCreate();
    stock.setRemark("8月工作日有效批次");
    stock.setCouponCodeMode(CouponCodeMode.WECHATPAY);
    
    StockSendRule stockSendRule = new StockSendRule();
    stockSendRule.setMaxCount(10000000L);
    stockSendRule.setMaxCountPerDay(100000L);
    stockSendRule.setMaxCountPerUser(1L);
    stock.setStockSendRule(stockSendRule);

    // 单券使用规则（scope=ALL时只配置券可核销时间，优惠规则在single_usage_info中）
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
    stock.setSingleUsageRule(singleUsageRule);

    // 使用规则展示信息
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
