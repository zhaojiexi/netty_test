package com.game.tcp_origin_byte.server3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;

public class YourServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelActive");
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    if (msg instanceof TextWebSocketFrame) {
      handleWebSocketFrame(ctx, (TextWebSocketFrame) msg);
    } else if (msg instanceof ByteBuf){
      ByteBuf msg1 = (ByteBuf) msg;
      System.out.println("Received message from client: " + msg1.toString(CharsetUtil.UTF_8));
    }
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ByteBuf channelReadCompleteMsg = Unpooled.copiedBuffer("server send msg to client",
        CharsetUtil.UTF_8);
    System.out.println("server send msg to client:" + channelReadCompleteMsg.toString(CharsetUtil.UTF_8));
    ctx.writeAndFlush(channelReadCompleteMsg);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 发生异常时，关闭连接
    cause.printStackTrace();
    ctx.close();
  }

  private void handleWebSocketFrame(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
    // Check for closing frame
    System.out.println(" client send msg =" + frame.text());
    System.out.println("Text frame received. Sending it back frame.retain() = " + frame.retain());
    ctx.channel().writeAndFlush(new TextWebSocketFrame(frame.text()));
  }
}