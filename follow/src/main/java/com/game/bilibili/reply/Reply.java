package com.game.bilibili.reply;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.bilibili.reply.pojo.Data;
import com.game.bilibili.reply.pojo.JsonRootBean;
import com.game.bilibili.reply.pojo.Replies;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author zhaojx
 * @date 2023/8/22 16:02
 */
public class Reply {

  public static void main(String[] args) {
    try {
      // 创建URL对象
      int i = 1;
      URL url = new URL(
          "https://api.bilibili.com/x/v2/reply/main?csrf=56a61ed32399b7c7267612e0e0aa5653&mode=3&oid=616376953&plat=1&type=1&next="
              + i);

      // 打开连接
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      // 设置请求方法
      connection.setRequestMethod("GET");

      // 发送请求
      int responseCode = connection.getResponseCode();
      System.out.println("Response Code: " + responseCode);

      // 读取响应
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
      String line;
      StringBuilder response = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();

      // 将包含JSON数据的字符串转换为JsonRootBean对象
      ObjectMapper objectMapper = new ObjectMapper();
      JsonRootBean jsonRootBean = objectMapper.readValue(reader.toString(), JsonRootBean.class);

      System.out.println(jsonRootBean);
      // 访问data字段
      Data data = jsonRootBean.getData();
      System.out.println(data);



      // 断开连接
      connection.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
