package com.game.textwebsocket.server2;

import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author zhaojx
 * @date 2023/8/21 17:39
 */
import io.netty.channel.ChannelHandlerContext;

public class YourServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelActive"+ctx.fireChannelActive());
  }


  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 发生异常时，关闭连接
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx,
      TextWebSocketFrame frame) throws Exception {
    System.out.println("channelRead0" + frame.text());
    ctx.channel().writeAndFlush(new TextWebSocketFrame(frame.text()));

  }
}