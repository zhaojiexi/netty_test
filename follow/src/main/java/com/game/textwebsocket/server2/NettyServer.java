package com.game.textwebsocket.server2;

/**
 * @author zhaojx
 * @date 2023/8/21 17:39
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer {

  private final int port;

  public NettyServer(int port) {
    this.port = port;
  }

  public void run() throws Exception {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG, 128)
          .childOption(ChannelOption.SO_KEEPALIVE, true)
          .childHandler(new ChannelInitializer<Channel>() {
            @Override
            public void initChannel(Channel ch) throws Exception {
              ch.pipeline()
                  .addLast(new LoggingHandler(LogLevel.TRACE))
                  // HttpRequestDecoder和HttpResponseEncoder的一个组合，针对http协议进行编解码
                  .addLast(new HttpServerCodec())
                  // 需要放到HttpServerCodec这个处理器后面
                  .addLast(new HttpObjectAggregator(10240))
                  /**
                   * 如果不加这个，Netty将无法识别传入的WebSocket连接，并将其视为普通的HTTP请求，所以不会响应
                   */
                  .addLast(new WebSocketServerProtocolHandler("/hello"))
                  .addLast(new YourServerHandler());
            }
          });

      ChannelFuture future = bootstrap.bind(port).sync();
      System.out.println("Server started and listening on port " + port);

      future.channel().closeFuture().sync();
    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) throws Exception {
    int port = 8080;

    NettyServer server = new NettyServer(port);
    server.run();
  }
}