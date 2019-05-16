package com.chenmual.netty.l_13_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,String msg) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + ", " + msg);
//		ctx.writeAndFlush(654321L);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		System.out.println(cause.getMessage());
	}
}
