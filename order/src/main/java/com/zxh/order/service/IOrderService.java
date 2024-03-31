package com.zxh.order.service;

import com.zxh.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxh.order.vo.OrderCreateVo;
import com.zxh.order.vo.getOrderIdVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxh
 * @since 2024-01-24
 */
public interface IOrderService extends IService<Order> {

    void orderCreate(OrderCreateVo vo);

    String getOrderId(getOrderIdVo vo);

    void orderChange(String orderId);
}
