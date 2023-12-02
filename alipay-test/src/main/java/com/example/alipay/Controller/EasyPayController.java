package com.example.alipay.Controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class EasyPayController {

    private final Config config;
    @GetMapping
    public String pay() throws Exception {

        Factory.setOptions(config);
        //调用支付宝接口
        AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace().preCreate("Iphone 15", "202012104031", "1999");
        //解析结果

        return null;
    }
}
