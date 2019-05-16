package com.chenmual.netty.l_15_handler3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer15 extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline = ch.pipeline();
		channelPipeline.addLast(new MyPersonDecoder());
		channelPipeline.addLast(new MyPersonEncoder());
		channelPipeline.addLast(new MyServerHandler());
	}
}
