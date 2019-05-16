package com.chenmual.netty.l_13_handler;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new MyLongToByteEncoder());
		pipeline.addLast(new MyByteToLongDecoder2());
		pipeline.addLast(new MyClientHandler());
	}
}
