package com.example.alipay.Controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@Slf4j
@AllArgsConstructor
public class EasyPayController {

    private final Config config;
    @GetMapping("/pay")
    public String pay() throws Exception {

        Factory.setOptions(config);
        //调用支付宝接口
        AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace().preCreate("Apple iPhone11 128G", "1234567890", "5799.00");
        //解析结果
        String httpBody = response.getHttpBody();
        //转json对象
        JSONObject jsonObject = JSONObject.parseObject(httpBody);
        String qrUrl = jsonObject.getJSONObject("alipay_trade_precreate_response").get("qr_code").toString();
        //生成二维码
        QrCodeUtil.generate(qrUrl,300,300,new File("D://pay.jpg"));

        return httpBody;
    }

    //回调接口
    @PostMapping("/notify")
    public String notify(HttpServletRequest request){
        log.info("收到支付成功通知");
        String out_trade_no = request.getParameter("out_trade_no");
        log.info("流水号{}",out_trade_no);
        //TODO 后续业务 发货
        return "success";
    }

    @GetMapping("/query")
    public String query() throws Exception {
        Factory.setOptions(config);
        AlipayTradeQueryResponse response = Factory.Payment.Common().query("3334567890");
        return response.getHttpBody();
    }

}
