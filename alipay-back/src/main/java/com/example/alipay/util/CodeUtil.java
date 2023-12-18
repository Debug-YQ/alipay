package com.example.alipay.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;

public class CodeUtil {
    public static void main(String[] args) {
        QrConfig qrConfig = new QrConfig(600, 600);
        qrConfig.setErrorCorrection(ErrorCorrectionLevel.H);
        qrConfig.setBackColor(Color.lightGray);
        QrCodeUtil.generate("https://baidu.com",qrConfig, FileUtil.file("d:/1.jpg"));
    }
}
