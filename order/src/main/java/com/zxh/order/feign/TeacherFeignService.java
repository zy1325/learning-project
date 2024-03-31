package com.zxh.order.feign;

import com.zxh.common.common.ResultData;
import com.zxh.order.config.FeignConfiguration;
import com.zxh.order.dto.UserCourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "teacher",configuration = FeignConfiguration.class)
public interface TeacherFeignService {
    @PostMapping("/userCourse/save")
   ResultData saveUserCourse(@RequestBody UserCourseDto to);
}
