package com.chenmual.netty.l_05_websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChannelInitialer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline = ch.pipeline();
		channelPipeline.addLast(new HttpServerCodec())
				.addLast(new ChunkedWriteHandler())//netty对服务器请求进行分块或者分段
				.addLast(new HttpObjectAggregator(8192))//对HTTP消息进行聚合 如果长度超过指定长度 HandleOversizeMessage就会被调用
				.addLast(new WebSocketServerProtocolHandler("/ws"))//负责websocket的握手 心跳.文本和二进制都会传递给下个自己实现的handler处理
				//"/ws"指的是ws://server:port/ws后面的那个ws
				.addLast(new TextWebSocketFrameHanlder());
	}
}
