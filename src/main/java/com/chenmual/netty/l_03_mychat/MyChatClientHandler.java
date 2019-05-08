package com.chenmual.netty.l_03_mychat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyChatClientHandler extends SimpleChannelInboundHandler {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,Object msg) throws Exception {
		System.out.println(msg);
	}
}
