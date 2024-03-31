package com.zxh.order.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfoVo {
    private BigDecimal price;
    private String name;
    private String orderId;
}
