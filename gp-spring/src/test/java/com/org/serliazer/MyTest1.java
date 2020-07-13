package com.org.serliazer;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyTest1 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket accept = serverSocket.accept();

            ObjectInputStream inputStream = new ObjectInputStream(accept.getInputStream());
            User object = (User) inputStream.readObject();
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
