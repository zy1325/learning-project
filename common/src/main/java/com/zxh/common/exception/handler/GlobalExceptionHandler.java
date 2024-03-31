package com.zxh.common.exception.handler;


import com.zxh.common.common.ResultData;
import com.zxh.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultData bizExceptionHandler(BizException e) {
        logger.error("发生异常，原因是：{}", e.getMsg());
        return ResultData.error(Integer.parseInt(e.getCode()), e.getMsg());
    }
}
