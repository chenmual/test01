package com.chenmual.netty.l_14_handler2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	private int count;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
//		System.out.println("client output" + msg);
//		ctx.writeAndFlush("from client:" + LocalDateTime.now());
		byte[] buffer = new byte[msg.readableBytes()];
		msg.readBytes(buffer);
		String message = new String(buffer, Charset.forName("utf-8"));
		System.out.println("客户端接收的消息:" + message + " 客户端接收的消息数量:" + (++this.count));

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//TCP粘包
		for(int i = 0; i < 10; i++){
			ByteBuf buffer = Unpooled.copiedBuffer("send from client",Charset.forName("utf-8"));
			ctx.writeAndFlush(buffer);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
