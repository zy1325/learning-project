package com.zxh.teacher.Feign;

import com.zxh.common.common.ResultData;
import com.zxh.teacher.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "backsystem",configuration = FeignConfiguration.class)
public interface SystemFeignService {
    @GetMapping("/type/{id}")
    ResultData getTypeById(@PathVariable Integer id);

    @GetMapping("/type/pid/{id}")
    ResultData getTypeIdsByPid(@PathVariable Integer id);

    @GetMapping("/user/{id}")
    ResultData getUserInfoById(@PathVariable Integer id);
}
