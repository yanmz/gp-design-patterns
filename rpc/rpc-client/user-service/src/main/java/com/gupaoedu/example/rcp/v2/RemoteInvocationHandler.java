package com.gupaoedu.example.rcp.v2;

import com.gupaoedu.example.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Tom
 */
@Component
public class RemoteInvocationHandler implements InvocationHandler {

    @Value("${gp.host}")
    private String localhost;
    @Value("${gp.port}")
    private int port;

    public RemoteInvocationHandler(String localhost, int port) {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //先建立远程连接
        RpcNetTransport rpcNetTransport = new RpcNetTransport(localhost, port);
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setArgs(args);
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setTypes(method.getParameterTypes());
        return rpcNetTransport.send(rpcRequest);
    }
}
