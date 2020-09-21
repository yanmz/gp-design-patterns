package org.atguigu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/9/9 16:24
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据实际（这里我们可以读取客户端发送的信息）
    //1.ChannelHandlerContext  :上下文对象 含有管道pipeline  通道channel地址
    //2.object msg ：就是 客户端发送的数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        System.out.println("服务端当前线程："+Thread.currentThread().getName());
        System.out.println("server ctx ="+ctx);

        //将 msg转成bytebuf
        //ByteBuf是netty提供的 不是你提供的bytebuffer
        ByteBuf buffer = (ByteBuf) msg;

        System.out.println("客户端发送的信息是："+buffer.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址："+ctx.channel().remoteAddress());
    }


    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        //将数据写入到缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello  客户端",CharsetUtil.UTF_8));
    }

    //处理异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
