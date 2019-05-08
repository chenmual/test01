package com.chenmual.netty.l_06_protobuf;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class TestClientInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline = ch.pipeline();

		channelPipeline.addLast(new ProtobufVarint32FrameDecoder());
		channelPipeline.addLast(new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
		channelPipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
		channelPipeline.addLast(new ProtobufEncoder());

		channelPipeline.addLast(new TestClientHandler());
	}
}
