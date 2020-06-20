package com.gupaoedu.example.rcp;

import com.gupaoedu.example.RpcRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Tom
 */
public class RemoteInvocationHandler implements InvocationHandler {
    private String localhost;
    private int  port;
    public RemoteInvocationHandler(String localhost,int port){
        this.localhost =localhost;
        this.port = port;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //先建立远程连接
        RpcNetTransport rpcNetTransport=new RpcNetTransport(localhost,port);
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setArgs(args);
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setTypes(method.getParameterTypes());
        return rpcNetTransport.send(rpcRequest);
    }
}
