package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserOrder {
    // 主键
    private Long orderid;
    // 订单编号
    private String ordernumber;
    // 用户ID
    private Long userid;
    // 创建时间
    private Date createTime;

    private String yearmonth;
}