# 微信支付商品券 Java Demo

自己对接微信支付商品券 API 时整理的示例代码，分享出来希望能帮到有同样需求的朋友。

## 为什么写这个

官方文档的 API 契约是按原子粒度组织的，参数很多很分散，对着文档拼参数拼得头疼。所以我把常见的 10 种券场景都跑通了一遍，整理成可直接运行的示例代码，方便大家参考。

## 支持的场景

- **商户类型**：品牌商户、服务商
- **使用模式**：单券（SINGLE）、多次优惠（PROGRESSIVE_BUNDLE）
- **券类型**：满减券、折扣券、兑换券
- **优惠范围**：全场券、单品券

## 项目结构

```
src/main/java/com/wechatpay/java/
├── utils/
│   └── WXPayBrandUtility.java      # 工具类：签名、验签、HTTP请求等
├── productcoupon/
│   ├── client/                      # API客户端
│   │   ├── BrandProductCouponClient.java    # 品牌商户客户端
│   │   └── PartnerProductCouponClient.java  # 服务商客户端
│   ├── model/                       # 数据模型
│   │   ├── entity/                  # 实体类
│   │   ├── enums/                   # 枚举类
│   │   ├── request/                 # 请求对象
│   │   └── response/                # 响应对象
│   ├── branddemo/                   # 品牌商户示例
│   │   ├── single/                  # 单券模式
│   │   └── many/                    # 多次优惠模式
│   └── partnerdemo/                 # 服务商示例
│       ├── single/                  # 单券模式
│       └── many/                    # 多次优惠模式
```

## 10 种券场景示例

| 使用模式 | 优惠范围 | 券类型 | 品牌商户示例 | 服务商示例 |
|---------|---------|--------|-------------|-----------|
| 单券 | 全场 | 折扣券 | `single/AllDiscount.java` | `single/AllDiscountSingle.java` |
| 单券 | 全场 | 满减券 | `single/AllNormal.java` | `single/AllNormalSingle.java` |
| 单券 | 单品 | 折扣券 | `single/SingleDiscount.java` | `single/SingleDiscountSingle.java` |
| 单券 | 单品 | 满减券 | `single/SingleNormal.java` | `single/SingleNormalSingle.java` |
| 单券 | 单品 | 兑换券 | `single/SingleExchange.java` | `single/SingleExchangeSingle.java` |
| 多次优惠 | 全场 | 折扣券 | `many/AllDiscountSequential.java` | `many/AllDiscountMany.java` |
| 多次优惠 | 全场 | 满减券 | `many/AllNormalSequential.java` | `many/AllNormalMany.java` |
| 多次优惠 | 单品 | 折扣券 | `many/SingleDiscountSequential.java` | `many/SingleDiscountMany.java` |
| 多次优惠 | 单品 | 满减券 | `many/SingleNormalSequential.java` | `many/SingleNormalMany.java` |
| 多次优惠 | 单品 | 兑换券 | `many/SingleExchangeSequential.java` | `many/SingleExchangeMany.java` |

## 快速开始

### 环境要求

- JDK 8+
- Maven 3.x

### 配置参数

改一下 Demo 类里的配置，换成你自己的：

**品牌商户** (`BrandProductCouponDemo.java`)：
```java
private static final String BRAND_ID = "你的品牌ID";
private static final String CERTIFICATE_SERIAL_NO = "品牌API证书序列号";
private static final String PRIVATE_KEY_PATH = "品牌API证书私钥路径";
private static final String WECHAT_PAY_PUBLIC_KEY_ID = "微信支付公钥ID";
private static final String WECHAT_PAY_PUBLIC_KEY_PATH = "微信支付公钥路径";
```

**服务商** (`PartnerProductCouponDemo.java`)：
```java
private static final String BRAND_ID = "品牌ID";
private static final String CERTIFICATE_SERIAL_NO = "商户API证书序列号";
private static final String PRIVATE_KEY_PATH = "商户API证书私钥路径";
private static final String WECHAT_PAY_PUBLIC_KEY_ID = "微信支付公钥ID";
private static final String WECHAT_PAY_PUBLIC_KEY_PATH = "微信支付公钥路径";
```

### 运行

```bash
# 编译
mvn clean compile

# 跑品牌商户示例
mvn exec:java -Dexec.mainClass="com.wechatpay.java.productcoupon.branddemo.BrandProductCouponDemo"

# 跑服务商示例
mvn exec:java -Dexec.mainClass="com.wechatpay.java.productcoupon.partnerdemo.PartnerProductCouponDemo"
```


## 注意事项

- 示例代码里的参数都是演示用的，**记得换成你自己的**
- 生产环境该有的参数校验、异常处理、日志记录别忘了加
- API 如果有更新，以官方文档为准，我不一定能及时同步

## 官方文档

### 品牌商户
- [单券开发指引](https://pay.weixin.qq.com/doc/brand/4016060894)
- [多次优惠开发指引](https://pay.weixin.qq.com/doc/brand/4016439305)
- [开发必要参数说明](https://pay.weixin.qq.com/doc/brand/4015415289)

### 服务商
- [单券开发指引](https://pay.weixin.qq.com/doc/v3/partner/4015788446)
- [多次优惠开发指引](https://pay.weixin.qq.com/doc/v3/partner/4016438816)
- [开发必要参数说明](https://pay.weixin.qq.com/doc/v3/partner/4015981654)

## 反馈

觉得有用的话给个 Star 呗~
