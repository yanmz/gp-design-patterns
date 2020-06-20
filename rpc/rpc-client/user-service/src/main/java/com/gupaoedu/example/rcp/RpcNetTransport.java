package com.gupaoedu.example.rcp;

import com.gupaoedu.example.RpcRequest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 调用服务端
 */
public class RpcNetTransport {
    private String localhost;
    private int port;

    public RpcNetTransport(String localhost, int port) {
        this.localhost = localhost;
        this.port = port;
    }
    public Socket newSocket() throws IOException {
        Socket socket=new Socket(localhost,port);
        return socket;
    }

    public Object send(RpcRequest request) throws IOException {
        ObjectOutputStream outputStream=null;
        ObjectInputStream inputStream=null;
        try {
            Socket socket  = newSocket();
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            return  inputStream.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
