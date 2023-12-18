package com.example.alipay.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "alipay.general")
public class GeneralPayProperties {

    // 支付宝网关（固定）
    private String url;
    // 三方代调用场景下传入第三方应用 APPID
    private String appId;
    // 三方代调用场景下传入第三方应用私钥 ，生成方式可查看 配置密钥https://opendocs.alipay.com/isv/02kipl?pathHash=39e766a5
    private String appPrivateKey;
    // 参数返回格式，只支持 JSON（固定）
    private String format;
    // 编码集，支持 GBK/UTF-8 开发者根据实际工程编码配置
    private String charset;
    // 三方代调用场景下传入第三方应用的支付宝公钥，由支付宝生成获取，查看 配置密钥https://opendocs.alipay.com/isv/02kipl?pathHash=39e766a5。
    private String alipayPublicKey;
    // 三方代调用场景下服务商生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2
    private String signType;

    //异步通知接收服务地址
    private String notifyUrl;
}
