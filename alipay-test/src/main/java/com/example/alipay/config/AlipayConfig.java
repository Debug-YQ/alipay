package com.example.alipay.config;

import com.alipay.easysdk.kernel.Config;
import com.example.alipay.prop.AliPayProperties;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AlipayConfig {

    @Bean
    public Config config(AliPayProperties payProperties) {
        Config config = new Config();
        config.protocol = payProperties.getProtocol();
        config.gatewayHost = payProperties.getGatewayHost();
        config.signType = payProperties.getSignType();
        config.appId = payProperties.getAppId();
        config.merchantPrivateKey = payProperties.getMerchantPrivateKey();
        config.alipayPublicKey = payProperties.getAlipayPublicKey();
        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = payProperties.getNotifyUrl();
        config.encryptKey = "";
        return config;
    }


}
