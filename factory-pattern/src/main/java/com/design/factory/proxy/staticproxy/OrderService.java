package com.design.factory.proxy.staticproxy;

public class OrderService implements IOrderService {
    private OrderDao orderDao;

    public OrderService() {
        //如果使用 Spring 应该是自动注入的 //为了使用方便，我们在构造方法中将 orderDao 直接初始化
        orderDao = new OrderDao();
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService 调用 orderDao 创建订单");
        return orderDao.insert(order);

    }
}
