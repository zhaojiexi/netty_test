package com.game.client2;

import io.netty.channel.ChannelHandler;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author zhaojx
 * @date 2023/8/21 17:37
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class YourClientHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    // 当客户端与服务器建立连接后，发送消息到服务器
    String message = "Hello, Server!";
    ByteBuf buffer = ctx.alloc().buffer();
    buffer.writeBytes(message.getBytes());
    ctx.writeAndFlush(buffer);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    // 当客户端接收到服务器发送的消息时，进行处理
    ByteBuf buffer = (ByteBuf) msg;
    byte[] bytes = new byte[buffer.readableBytes()];
    buffer.readBytes(bytes);
    String message = new String(bytes);
    System.out.println("Received message from server: " + message);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 发生异常时，关闭连接
    cause.printStackTrace();
    ctx.close();
  }
}