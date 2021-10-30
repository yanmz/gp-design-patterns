package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class Bootstrap {


    public static void main(String[] args) {
        //SPRING BOOT
        IOrderService orderService=new OrderServiceImpl();
        RpcProxyServer rpcProxyServer=new RpcProxyServer();
        rpcProxyServer.publisher(orderService,8080);
    }
}
