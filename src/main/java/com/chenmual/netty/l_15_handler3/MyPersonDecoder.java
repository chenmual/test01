package com.chenmual.netty.l_15_handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyPersonDecoder extends ReplayingDecoder<Void> {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,List<Object> out) throws Exception {
		//拿到消息长度
		System.out.println("my decoder started");
		int length = in.readInt();

		byte[] content = new byte[length];
		in.readBytes(content);

		PersonProtocol personProtocol = new PersonProtocol();
		personProtocol.setLength(length);
		personProtocol.setContent(content);

		out.add(personProtocol);
	}
}
