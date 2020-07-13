package com.gupaoedu.example.rcp;

import com.gupaoedu.example.IOrderService;

public class App {
    public static void main(String[] args) {
        RpcProxyClient proxyClient = new RpcProxyClient();
        IOrderService orderService = proxyClient.clientProxy(IOrderService.class, "localhost", 8080);
        System.out.println(orderService.queryOrderList());
        System.out.println(orderService.orderById("Mic"));
    }
}
