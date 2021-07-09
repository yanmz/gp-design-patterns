package org.atguigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * 聊天室服务端
 * 3.2
 *
 * @author zhangfan
 */
public class ChatServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private InetSocketAddress address;
    private int port;

    public ChatServer(int port) {
        this.port = port;
        try {
            address = new InetSocketAddress(port);
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(address);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void work() throws IOException {
        while (true) {
            int select = selector.select();
            if (select > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        SocketChannel channel = serverSocketChannel.accept();
//拿到的channel一定要记得设置非阻塞
                        channel.configureBlocking(false);
                        SocketAddress address = channel.getRemoteAddress();
                        System.out.println(address + "已上线");
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        readMsg(key);
                    }
                    iterator.remove();
                }
            }
        }

    }

    private void readMsg(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
//拿到的channel一定要记得设置非阻塞
            channel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
            int read = channel.read(buffer);
            buffer.flip();
            if (read > 0) {
                SocketAddress remoteAddress = channel.getRemoteAddress();
                StringBuilder builder = new StringBuilder(remoteAddress.toString() + "说：");
                builder.append(new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
                String msg = builder.toString();
                System.out.println(msg);
                sendMsg(msg, channel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                SocketAddress remoteAddress = channel.getRemoteAddress();
                System.out.println(remoteAddress + "离线了");
                key.cancel();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 服务器把来自各个客户端的消息发送给所有客户端，要排除消息源客户端
     *
     * @param msg
     * @param excludeChannel 对应消息源的客户端通道
     */
    private void sendMsg(String msg, SocketChannel excludeChannel) {
        if (msg != null) {
            Set<SelectionKey> keys = selector.keys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                SelectableChannel targetChannel = key.channel();
                if (targetChannel instanceof SocketChannel && targetChannel != excludeChannel) {
                    SocketChannel channel = (SocketChannel) targetChannel;
                    try {
                        channel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer(9527);
        server.work();
    }
}