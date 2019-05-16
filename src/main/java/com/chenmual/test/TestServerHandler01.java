package com.chenmual.test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler01 extends SimpleChannelInboundHandler {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,Object msg) throws Exception {
		Channel ch = ctx.channel();
		ch.writeAndFlush(msg);
	}
}
