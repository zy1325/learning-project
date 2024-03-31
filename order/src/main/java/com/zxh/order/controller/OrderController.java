package com.zxh.order.controller;

import com.zxh.common.common.ResultData;
import com.zxh.order.service.IOrderService;
import com.zxh.order.vo.OrderCreateVo;
import com.zxh.order.vo.getOrderIdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxh
 * @since 2024-01-24
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping("/create")
    public ResultData orderCreate(@RequestBody OrderCreateVo vo){
        orderService.orderCreate(vo);
        return ResultData.ok();
    }

    @PostMapping("/getOrderId")
    public ResultData getOrderId(@RequestBody getOrderIdVo vo){
        String orderId = orderService.getOrderId(vo);
        return ResultData.ok().setData(orderId);
    }
    //购买成功修改订单状态为已支付
    @PostMapping("/change")
    public void orderChange(@RequestParam("out_trade_no") String s){
        orderService.orderChange(s);
    }

}
