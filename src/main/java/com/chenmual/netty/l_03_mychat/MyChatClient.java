package com.chenmual.netty.l_03_mychat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyChatClient {
	public static void main(String[] args) throws Exception{
			EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
			try {
				Bootstrap bootstrap = new Bootstrap();
				bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
						.handler(new MyChatClientInitializer());//客户端一般没有worker所以只使用handler就足够了 不需要childhadler

				Channel channel = bootstrap.connect("localhost", 8899).sync().channel();

				//建立好链接后 不断读取控制台输入的内容
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				for(;;){
					channel.writeAndFlush(br.readLine() + "\r\n");
				}
//				channelFuture.channel().closeFuture().sync();
			} finally {
				eventLoopGroup.shutdownGracefully();
			}
	}
}
