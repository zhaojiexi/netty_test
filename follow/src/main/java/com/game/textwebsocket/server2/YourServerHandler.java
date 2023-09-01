package com.game.textwebsocket.server2;

import com.game.textwebsocket.UserManager;
import com.game.textwebsocket.proto.TextMessage;
import com.google.gson.Gson;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author zhaojx
 * @date 2023/8/21 17:39
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;

public class YourServerHandler extends SimpleChannelInboundHandler<Object> {

  Gson gson = new Gson();

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelActive:" + ctx.fireChannelActive());
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 发生异常时，关闭连接
    UserManager.INSTANCE.userClear(ctx);
    System.out.println("用户异常：" + cause.getMessage());
    ctx.close();
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("用户离开");
    UserManager.INSTANCE.userClear(ctx);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx,
      Object frame) throws Exception {
    if (frame instanceof TextWebSocketFrame) {
      String text = ((TextWebSocketFrame) frame).text();
      TextMessage textMessage = gson.fromJson(text, TextMessage.class);
      UserManager.INSTANCE.setMap(ctx, textMessage);

      if (textMessage.getSendUserId() == -1) {
        // 全局广播
        UserManager.INSTANCE.sendAll(textMessage);
      } else {
        // 单发
        UserManager.INSTANCE.sendUser(textMessage);
      }
    }
  }


  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleState) {
      IdleState idleState = (IdleState) evt;
      switch (idleState) {
        case READER_IDLE:
          System.out.println("read idle");
          break;
        case WRITER_IDLE:
          System.out.println("write idle");
          break;
        case ALL_IDLE:
          System.out.println("all idle close");
          break;
      }
    }
    System.out.println("userEventTriggered:" + evt);
  }
}