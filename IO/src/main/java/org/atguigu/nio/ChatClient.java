package org.atguigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 聊天室客户端
 * 3.2
 *
 * @author zhangfan
 */
public class ChatClient {
    private InetSocketAddress address;
    private SocketChannel socketChannel;
    private Selector selector;

    public ChatClient(String host, int port) {
        try {
            address = new InetSocketAddress(host, port);
            socketChannel = SocketChannel.open(address);
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            while (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
                        channel.read(buffer);
                        buffer.flip();
                        String msg = new String(buffer.array(), 0, buffer.limit());
                        System.out.println(msg);
                        buffer.clear();
//注意必须移除用完的选择键
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String msg = scanner.next();
                if ("exit".equals(msg.toLowerCase().trim())) {
                    socketChannel.close();
                }
                socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final ChatClient client = new ChatClient("localhost", 9527);
        new Thread(() -> {
            client.read();
        }).start();
        client.send();
    }
}