package com.example.alipay.Controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.example.alipay.prop.GeneralPayProperties;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
public class GeneralPayController {
    @Resource
    private final GeneralPayProperties genPayProperties;
    @GetMapping("/alipaytest")
    public String alipaytest() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                genPayProperties.getUrl(),
                genPayProperties.getAppId(),
                genPayProperties.getAppPrivateKey(),
                genPayProperties.getFormat(),
                genPayProperties.getCharset(),
                genPayProperties.getAlipayPublicKey(),
                genPayProperties.getSignType());
        //获得初始化的AlipauCient
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        //异步回调地址
        request.setNotifyUrl(genPayProperties.getNotifyUrl());
        Map<String,String> reauetParam = new HashMap<>();
        reauetParam.put("out_trade_no","1231231242342345");
        reauetParam.put("total_amount","12314.12");
        reauetParam.put("subject","iphone");
        String param = JSONObject.toJSONString(reauetParam);

        request.setBizContent(param);
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        //解析结果
        String jsonString = JSON.toJSONString(response);

        if(response.isSuccess()){
            System.out.println("调用成功");

//            转json对象
            JSONObject jsonObject = JSONObject.parseObject(jsonString);

            String qrUrl = jsonObject.getJSONObject("body").getJSONObject("alipay_trade_precreate_response").get("qr_code").toString();
            //生成二维码
            QrCodeUtil.generate(qrUrl,300,300,new File("D://pay.jpg"));

        } else {
            System.out.println("调用失败");
        }
        return jsonString;
    }

}
