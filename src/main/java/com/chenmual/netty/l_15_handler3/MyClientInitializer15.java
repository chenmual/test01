package com.chenmual.netty.l_15_handler3;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer15 extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new MyPersonDecoder());
		pipeline.addLast(new MyPersonEncoder());
		pipeline.addLast(new MyClientHandler());
	}
}
