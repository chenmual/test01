package com.chenmual.netty.l_15_handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {
	@Override
	protected void encode(ChannelHandlerContext ctx,PersonProtocol msg,ByteBuf out) throws Exception {
		System.out.println("my encoder started");
		out.writeInt(msg.getLength());//先写长度
		out.writeBytes(msg.getContent());//再写内容
	}

}
