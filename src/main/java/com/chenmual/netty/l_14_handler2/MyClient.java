package com.chenmual.netty.l_14_handler2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		try {
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
					.handler(new MyClientInitializer14());

			ChannelFuture channelFuture = bootstrap.connect("localhost", 8167).sync();
			channelFuture.channel().closeFuture().sync();
		}finally {
			eventLoopGroup.shutdownGracefully();
		}

	}
}
