package com.chenmual.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler02 extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,String msg) throws Exception {
		ctx.channel().writeAndFlush(msg);
	}
}
