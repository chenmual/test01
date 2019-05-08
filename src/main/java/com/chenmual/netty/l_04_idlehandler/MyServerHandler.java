package com.chenmual.netty.l_04_idlehandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 默认会调用ChannelHandlerContext的fireEventTriggered转发给下一个channelInboundHandler
	 * @param ctx
	 * @param evt
	 * @throws Exception
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx,Object evt) throws Exception {
		if(evt instanceof IdleStateEvent){
			IdleStateEvent event = (IdleStateEvent)evt;

			String eventType = null;
			//channel的三个状态(读空闲,写空闲,读写空闲)
			switch(event.state()){
				case READER_IDLE:
					eventType = "读空闲";
					break;
				case WRITER_IDLE:
					eventType = "写空闲";
					break;
				case ALL_IDLE:
					eventType = "读写空闲";
					break;
			}
			System.out.println(ctx.channel().remoteAddress() + " 超时事件:" + eventType);
			ctx.channel().close();
		}
	}
}
