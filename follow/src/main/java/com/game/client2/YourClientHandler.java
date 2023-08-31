package com.game.client2;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author zhaojx
 * @date 2023/8/21 17:37
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netscape.javascript.JSObject;

public class YourClientHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {

    // 当客户端与服务器建立连接后，发送消息到服务器
    String message = "Hello, Server!";
    ByteBuf buffer = ctx.alloc().buffer();
    buffer.writeBytes(message.getBytes());
    ctx.writeAndFlush(buffer);
  }


  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      TextWebSocketFrame textWebSocketFrame) throws Exception {
    System.out.println("client channelRead0:" + textWebSocketFrame.text());
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 发生异常时，关闭连接
    cause.printStackTrace();
    ctx.close();
  }
}