package com.chenmual.netty.l_13_handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {
	@Override
	protected void decode(ChannelHandlerContext ctx,ByteBuf in,List<Object> out) throws Exception {
		//不需要检查bytebuf的长度够不够读取
		System.out.println("new decoder");
		out.add(in.readLong());
	}
}
