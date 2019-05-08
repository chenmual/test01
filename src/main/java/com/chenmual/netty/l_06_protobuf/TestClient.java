package com.chenmual.netty.l_06_protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TestClient {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {

			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
					.handler(new TestClientInitializer());

			ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
			channelFuture.channel().closeFuture().sync();
//			Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
		}finally {
			eventLoopGroup.shutdownGracefully();
		}

	}
}
