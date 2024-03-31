package com.zxh.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbitmq.client.Channel;
import com.zxh.order.dto.UserCourseDto;
import com.zxh.order.entity.Order;
import com.zxh.order.feign.TeacherFeignService;
import com.zxh.order.mapper.OrderMapper;
import com.zxh.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxh.order.vo.OrderCreateVo;
import com.zxh.order.vo.getOrderIdVo;
import org.aspectj.weaver.ast.Or;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxh
 * @since 2024-01-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    TeacherFeignService feignService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void orderCreate(OrderCreateVo vo) {
        getOrderIdVo getOrderIdVo = new getOrderIdVo();
        BeanUtils.copyProperties(vo,getOrderIdVo);
        String alreadyOrderId= orderMapper.getOrderId(getOrderIdVo);
        String state = orderMapper.getState(alreadyOrderId);
        if(alreadyOrderId == null|| state.equals("2")){
            Order order = new Order();
            order.setCourseId(vo.getCourseId());
            order.setUserId(vo.getUserId());
            order.setCreateTime(LocalDateTime.now());
            order.setState("0");
            String orderId = UUID.randomUUID().toString().replace("-", "");
            order.setOrderId(orderId);
            orderMapper.createOrder(order);
            rabbitTemplate.convertAndSend("order-event-exchange","order.create.order",order.getOrderId());
        }
    }

    @Override
    public String getOrderId(getOrderIdVo vo) {
         String orderId = orderMapper.getOrderId(vo);
        return orderId;
    }

    @Override
    public void orderChange(String orderId) {
        orderMapper.orderChange(orderId);
        UserCourseDto userCourseDto = orderMapper.getUserCourse(orderId);
        feignService.saveUserCourse(userCourseDto);
    }


    @RabbitListener(queues = "order.release.order.queue")
    public void listener(String OrderId,Channel channel,Message message) throws IOException {
        System.out.println("收到过期的订单,准备关闭订单" + OrderId);
        String state = orderMapper.getState(OrderId);
        if(state.equals("0")){
            orderMapper.updateByOrderId(OrderId);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
