package com.chenmual.netty.l_02_withclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * 泛型可以定义为需要的类型比如httpObject(request)
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,String msg) {
		System.out.println(ctx.channel().remoteAddress() + ", " + msg);
		//--------把结果返回给客户端
		ctx.channel().writeAndFlush("from server: " + UUID.randomUUID());//把结果写进流并缓冲出去
	}

	/**
	 * 出现异常情况下怎么办
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();//关闭链接
	}
}
