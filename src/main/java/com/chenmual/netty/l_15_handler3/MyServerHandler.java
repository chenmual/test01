package com.chenmual.netty.l_15_handler3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import sun.security.util.Length;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {
	private int count = 0;//错误 count只存在于一个pipline里的handler
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
		int length = msg.getLength();
		byte[] content = msg.getContent();


		System.out.println("服务端接收到的数据:");
		System.out.println("长度:" + length);
		System.out.println("内容:" + new String(content, Charset.forName("utf-8")));

		System.out.println("服务端接收到的消息数量:" + (++this.count));


		String responseMessage = UUID.randomUUID().toString();
		int responseLength = responseMessage.getBytes("utf-8").length;
		byte[] responseContent = responseMessage.getBytes("utf-8");
		PersonProtocol personProtocol = new PersonProtocol();
		personProtocol.setLength(responseLength);
		personProtocol.setContent(responseContent);

		ctx.writeAndFlush(personProtocol);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}


}
