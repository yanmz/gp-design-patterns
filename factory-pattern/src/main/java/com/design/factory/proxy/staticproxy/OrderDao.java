package com.design.factory.proxy.staticproxy;

public class OrderDao {
    public int insert(Order order){
        System.out.println("OrderDao创建 Order 成功!");
        return 1;
    }
}
