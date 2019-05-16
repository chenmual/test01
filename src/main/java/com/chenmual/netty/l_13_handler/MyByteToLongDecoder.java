package com.chenmual.netty.l_13_handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext ctx,ByteBuf in,List<Object> out) throws Exception {
		System.out.println("decode被调用");
		System.out.println(in.readableBytes());
		//in来源
		//out目标
		if(in.readableBytes() >= 8){
			//可以读取long类型
			out.add(in.readLong());
		}
	}
}
