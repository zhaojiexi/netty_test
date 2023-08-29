package com.game.tcp_origin_byte.client3;

/**
 * @author zhaojx
 * @date 2023/8/21 17:37
 */
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class YourClientHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    System.out.println("Client active");
    // 当客户端与服务器建立连接后，发送消息到服务器
    ByteBuf byteBuf = Unpooled.copiedBuffer("hello,server!", CharsetUtil.UTF_8);
    ctx.writeAndFlush(byteBuf);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("Client receive message");
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