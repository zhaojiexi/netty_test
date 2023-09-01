package com.game.textwebsocket.proto;

/**
 * @author zhaojx
 * @date 2023/9/1 10:11
 */

public class TextMessage {
  private int id;
  private int sendUserId;
  private String text;

  public TextMessage(int id, int sendUserId, String text) {
    this.id = id;
    this.sendUserId = sendUserId;
    this.text = text;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSendUserId() {
    return sendUserId;
  }

  public void setSendUserId(int sendUserId) {
    this.sendUserId = sendUserId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
