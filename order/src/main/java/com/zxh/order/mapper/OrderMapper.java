package com.zxh.order.mapper;

import com.zxh.order.dto.UserCourseDto;
import com.zxh.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxh.order.vo.getOrderIdVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxh
 * @since 2024-01-24
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void createOrder(@Param("order")Order order);

    String getOrderId(@Param("vo")getOrderIdVo vo);

    void orderChange(@Param("orderId")String orderId);

    UserCourseDto getUserCourse(@Param("orderId")String orderId);

    String getState(@Param("orderId")String orderId);

    void updateByOrderId(@Param("orderId")String orderId);
}
