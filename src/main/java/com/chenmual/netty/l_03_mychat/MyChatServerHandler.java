package com.chenmual.netty.l_03_mychat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

	//存储实例
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx,String msg) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.forEach(ch -> {
			if(channel != ch) {
				//如果channel不是group里的(是别人), 则发消息
				ch.writeAndFlush(channel.remoteAddress() + " 发的消息:" + msg + "\n");
			}else{
				ch.writeAndFlush("[自己] " + msg + "\n");
			}
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	//客户端向服务器建立链接时进来
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		//向channelGroup里的其他channel广播
		channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " 加入\n");

		channelGroup.add(channel);//把新的链接放入channelGroup里
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		//当失去链接时
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " 离开\n");

		//自动调用channelGroup.remove()移除断掉的channel
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress() + " 上线");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress() + " 下线");
	}
}
