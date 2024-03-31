package com.zxh.order.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AlipayConfig {
    @Value("${alipay.appId}")
    private String appId;
    @Value("${alipay.appPrivateKey}")
    private String appPrivateKey;
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;

    @Bean
    public AlipayClient AlipayClient(){
        return new DefaultAlipayClient("https://openapi-sandbox.dl.alipaydev.com/gateway.do", appId, appPrivateKey, "json", "UTF-8", alipayPublicKey, "RSA2");
    }
}