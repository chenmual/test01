package com.chenmual.netty.l_02_withclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,String msg) {
		//服务器端向客户端发消息
		System.out.println(ctx.channel().remoteAddress());
		System.out.println("client oupt" + msg);
		ctx.writeAndFlush("from client" + LocalDateTime.now());//向服务器端回复一个标准UTP时间
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("来自客户端的问候");
	}
}
