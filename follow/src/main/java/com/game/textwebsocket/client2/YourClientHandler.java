package com.game.textwebsocket.client2;

import com.game.textwebsocket.ScheduleUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author zhaojx
 * @date 2023/8/21 17:37
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class YourClientHandler extends SimpleChannelInboundHandler<Object> {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
    // 当客户端与服务器建立连接后，发送消息到服务器
    ctx.writeAndFlush(new TextWebSocketFrame("Hello, Server!"));
    System.out.println("channelActive: Hello, Server!");
    // 链接建立后，发送ping
   /* ScheduleUtil.INSTANCE.scheduleAtFixedRateWithMills(() -> {
      System.out.println("client ping");
      ctx.channel().writeAndFlush(new PingWebSocketFrame());
      ctx.channel().writeAndFlush(new TextWebSocketFrame("Hello, Server!"));
    }, 0, 5000L);*/
  }


  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      Object textWebSocketFrame) throws Exception {
    if (textWebSocketFrame instanceof TextWebSocketFrame) {
      System.out.println("client channelRead0:" + ((TextWebSocketFrame) textWebSocketFrame).text());
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 发生异常时，关闭连接
    cause.printStackTrace();
    ctx.close();
  }
}