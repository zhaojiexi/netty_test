package com.game.tcp_origin_byte.client3;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import java.io.IOException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class NettyClient {

  private final String host;
  private final int port;

  public NettyClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void start() throws InterruptedException {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(group)
          .channel(NioSocketChannel.class)
          .option(ChannelOption.TCP_NODELAY, true)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
              ch.pipeline()
                  .addLast(new YourClientHandler());
            }
          });
      ChannelFuture future = bootstrap.connect(host, port).sync();
      if (future.isSuccess()) {
        startConsoleInput(future.channel());
      }
      future.channel().closeFuture().sync();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      group.shutdownGracefully();
    }
  }

  private static void startConsoleInput(Channel channel) throws IOException {
    Terminal terminal = TerminalBuilder.terminal();
    LineReader lineReader = LineReaderBuilder.builder()
        .terminal(terminal)
        .build();

    String prompt = "Enter your message (exit to quit): ";
    String line;
    while ((line = lineReader.readLine(prompt)) != null) {
      if (line.equalsIgnoreCase("exit")) {
        channel.close().syncUninterruptibly();
        break;
      }
      channel.writeAndFlush(Unpooled.copiedBuffer(line, CharsetUtil.UTF_8));

      prompt = "Enter your message (exit to quit): ";
    }
  }

  public static void main(String[] args) throws Exception {
    String host = "localhost";
    int port = 8080;

    NettyClient client = new NettyClient(host, port);
    client.start();
  }
}
