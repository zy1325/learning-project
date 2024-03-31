package com.zxh.teacher.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Data
@Configuration(proxyBeanMethods = false)
public class AliyunOssConfig {
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.access-key}")
    private String accesskey;
    @Value("${oss.secret-key}")
    private String secretkey;
    @Value("${oss.bucket}")
    private String bucket;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE )
    public OSS ossClient(){

        return new OSSClientBuilder().build(endpoint,accesskey,secretkey);
    }
}
