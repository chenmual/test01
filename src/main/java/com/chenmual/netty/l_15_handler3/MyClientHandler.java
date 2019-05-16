package com.chenmual.netty.l_15_handler3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {
	private int count;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,PersonProtocol msg) throws Exception {
		int length = msg.getLength();
		byte[] content = msg.getContent();
		System.out.println("客户端收到消息:");
		System.out.println("长度:" + length);
		System.out.println("内容:" + (new String(content, Charset.forName("utf-8"))));

		System.out.println("客户端收到消息数量:" + (++this.count));
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//TCP粘包
		for(int i = 0; i < 10; i++){
			String messageToBeSent = "SendMessageFromClient:" + i;
			byte[] content = messageToBeSent.getBytes();
			int len = content.length;
			PersonProtocol personProtocol = new PersonProtocol();
			personProtocol.setLength(len);
			personProtocol.setContent(content);
			ctx.writeAndFlush(personProtocol);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
