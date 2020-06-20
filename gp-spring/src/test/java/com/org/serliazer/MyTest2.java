package com.org.serliazer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MyTest2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9999);
        User u = new User();
        u.setAge(18);
        u.setName("xxxx");
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(u);
    }
}
