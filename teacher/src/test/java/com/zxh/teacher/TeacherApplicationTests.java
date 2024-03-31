package com.zxh.teacher;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.zxh.teacher.config.AliyunOssConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class TeacherApplicationTests {

	@Autowired
	AliyunOssConfig aliyunOssConfig;

	@Test
	void contextLoads() throws FileNotFoundException {
				// 创建OSSClient实例。
				OSS ossClient = aliyunOssConfig.ossClient();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dir = simpleDateFormat.format(new Date()) + "/";
		try {
					InputStream fileInputStream = new FileInputStream("E:\\demo\\learning-project\\src\\assets\\image\\1.png");
					ossClient.putObject(aliyunOssConfig.getBucket(), dir + "1"+ UUID.randomUUID().toString()+".png",fileInputStream);
				} catch (OSSException oe) {
					System.out.println("Caught an OSSException, which means your request made it to OSS, "
							+ "but was rejected with an error response for some reason.");
					System.out.println("Error Message:" + oe.getErrorMessage());
					System.out.println("Error Code:" + oe.getErrorCode());
					System.out.println("Request ID:" + oe.getRequestId());
					System.out.println("Host ID:" + oe.getHostId());
				} catch (ClientException ce) {
					System.out.println("Caught an ClientException, which means the client encountered "
							+ "a serious internal problem while trying to communicate with OSS, "
							+ "such as not being able to access the network.");
					System.out.println("Error Message:" + ce.getMessage());
				} finally {
					if (ossClient != null) {
						ossClient.shutdown();
						System.out.println("上传成功");
					}
				}


	}

}
