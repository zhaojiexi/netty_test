package com.game.textwebsocket;

import com.game.textwebsocket.proto.TextMessage;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhaojx
 * @date 2023/9/1 16:00
 */
public enum UserManager {
  INSTANCE;

  private Map<Integer, Channel> userMap = new ConcurrentHashMap<>();

  private Map<String, Integer> channelIdMap = new ConcurrentHashMap<>();

  public void addUser(Integer userId, Channel channel) {
    userMap.put(userId, channel);
  }

  public void removeUser(Integer userId) {
    userMap.remove(userId);
  }

  // 是否在线
  public boolean isOnline(Integer userId) {
    return userMap.containsKey(userId);
  }

  public void sendAll(TextMessage textMessage) {
    userMap.forEach((k, v) -> {
      if (k != textMessage.getId()) {
        v.writeAndFlush(new TextWebSocketFrame(beanToString(textMessage)));
      }
    });
  }

  public void sendUser(TextMessage textMessage) {
    Channel channel = userMap.get(textMessage.getSendUserId());
    if (channel != null) {
      channel.writeAndFlush(new TextWebSocketFrame(beanToString(textMessage)));
    }
  }

  public void setMap(ChannelHandlerContext ctx, TextMessage textMessage) {
    if (!isOnline(textMessage.getId())) {
      addUser(textMessage.getId(), ctx.channel());
      channelIdMap.put(ctx.channel().id().asLongText(), textMessage.getId());
    }
  }

  public void userClear(ChannelHandlerContext ctx) {
    Integer integer = channelIdMap.get(ctx.channel().id().asLongText());
    if (integer != null) {
      removeUser(integer);
    }
    userMap.entrySet().forEach(e -> {
      Channel c = e.getValue();
      c.writeAndFlush(
          new TextWebSocketFrame(beanToString(new TextMessage(integer, e.getKey(), "用户下线了"))));
    });

  }

  private String beanToString(TextMessage textMessage) {
    Gson gson = new Gson();
    return gson.toJson(textMessage);
  }
}
