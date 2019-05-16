package com.chenmual.netty.l_13_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class MyLongToStringDecoder extends MessageToMessageDecoder<Long> {
	@Override
	protected void decode(ChannelHandlerContext ctx,Long msg,List<Object> out) throws Exception {
		System.out.println("long to str decoder");
		out.add(String.valueOf(msg));
	}
}
