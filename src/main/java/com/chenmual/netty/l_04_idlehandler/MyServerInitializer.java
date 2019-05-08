package com.chenmual.netty.l_04_idlehandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//责任链模式
		// 在X之内,读写/读/写就会触发事件
		pipeline.addLast(new IdleStateHandler(5, 7, 10,TimeUnit.SECONDS));//空闲检测处理器
		pipeline.addLast(new MyServerHandler());
	}
}
