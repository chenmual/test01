package com.chenmual.netty.l_13_handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline = ch.pipeline();
		channelPipeline.addLast(new MyByteToLongDecoder2());//需要一个解码器
		channelPipeline.addLast(new MyLongToStringDecoder());
		channelPipeline.addLast(new MyLongToByteEncoder());
		channelPipeline.addLast(new MyServerHandler());
	}
}
