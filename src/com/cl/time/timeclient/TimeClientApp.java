package com.cl.time.timeclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClientApp {
    private int port=8080;
    private String address = "127.0.0.1";

    public TimeClientApp() {
    }

    public void run() throws Exception{
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeClientHandler());
                        }
                    }).option(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture channelFuture = clientBootstrap.connect(address, port).sync();

            channelFuture.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new TimeClientApp().run();
    }
}
