package com.game.tcp_origin_byte.client3;

/**
 * @author zhaojx
 * @date 2023/8/21 17:37
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class YourClientHandler2 extends SimpleChannelInboundHandler<TextWebSocketFrame> {

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
    if (msg instanceof TextWebSocketFrame) {
      System.out.println("Received message from server: textWebSocketFrame" + msg.toString());
    } else {
      // 当客户端接收到服务器发送的消息时，进行处理
      ByteBuf buffer = (ByteBuf) msg;
      byte[] bytes = new byte[buffer.readableBytes()];
      buffer.readBytes(bytes);
      String message = new String(bytes);
      System.out.println("Received message from server: " + message);
    }
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      TextWebSocketFrame textWebSocketFrame) throws Exception {
    System.out.println("Received message from server: textWebSocketFrame" + textWebSocketFrame);
  }


  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
      System.out.println("web socket 握手成功");
      WebSocketServerProtocolHandler.HandshakeComplete handshakeComplete = (WebSocketServerProtocolHandler.HandshakeComplete) evt;
      String requestUri = handshakeComplete.requestUri();
      String subproTocol = handshakeComplete.selectedSubprotocol();
    } else {
      System.out.println("web socket 握手成功");
      super.userEventTriggered(ctx, evt);
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 发生异常时，关闭连接
    cause.printStackTrace();
    ctx.close();
  }
}