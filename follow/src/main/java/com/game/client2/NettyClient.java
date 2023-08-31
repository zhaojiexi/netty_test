package com.game.client2;

import com.game.client2.YourClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
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

  public void start(String uri) throws InterruptedException {
    EventLoopGroup group = new NioEventLoopGroup();

    try {
      URI wsUri = new URI(uri);
      final WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(
          wsUri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders());



      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(group)
          .channel(NioSocketChannel.class)
          .option(ChannelOption.TCP_NODELAY, true)
          .handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) {
              ch.pipeline()
                  .addLast(new LoggingHandler(LogLevel.TRACE))
                  // HttpRequestDecoder和HttpResponseEncoder的一个组合，针对http协议进行编解码
                  .addLast(new HttpClientCodec())
                  // 需要放到HttpServerCodec这个处理器后面
                  .addLast(new HttpObjectAggregator(10240))
                  .addLast(new WebSocketClientProtocolHandler(handshaker))
                  .addLast(new YourClientHandler())
              ;
            }
          });

      ChannelFuture future = bootstrap.connect(host, port).sync();
      if (future.isSuccess()) {
        if (future.channel().isActive()) {
          System.out.println("客户端连接成功");
        }
        future.channel().writeAndFlush(Unpooled.copiedBuffer("HelloNetty", CharsetUtil.UTF_8));
        System.out.println("Connected to server successfully!");

        startConsoleInput(future.channel());
      }

      future.channel().closeFuture().sync();
    } catch (URISyntaxException | IOException e) {
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

//      String message = line;
//      ByteBuf buffer = channel.alloc().buffer();
//      buffer.writeBytes(message.getBytes());
//      channel.writeAndFlush(buffer);
      channel.writeAndFlush(new TextWebSocketFrame(line));

      prompt = "Enter your message (exit to quit): ";
    }
  }

  public static void main(String[] args) throws Exception {
    String host = "localhost";
    int port = 8080;

    NettyClient client = new NettyClient(host, port);
    client.start("ws://127.0.0.1:8080/hello");
  }

}
