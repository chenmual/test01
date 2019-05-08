package com.chenmual.netty.l_01_nettysample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) {
		ChannelPipeline pipeline = ch.pipeline();//拿到管道
		pipeline.addLast("httpServerCodec", new HttpServerCodec());//netty提供的处理器 需要单例
		//HttpServerCodec是httpRequestDecoder和httpResponseEncoder的一个组合
		pipeline.addLast("TestHttpServerHandler", new TestHttpServerHandler());
	}
}

