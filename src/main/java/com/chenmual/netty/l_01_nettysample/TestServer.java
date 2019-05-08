package com.chenmual.netty.l_01_nettysample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
	public static void main(String[] args) throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();//事件循环组 异步IO线程组 接受链接 转给WORKER
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			//启动服务
			ServerBootstrap serverBootstrap = new ServerBootstrap();

			serverBootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)//通过反射创建
					.childHandler(new TestServerInitializer());//自己定义的初始化器

			ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();//同步监听哪个端口

			channelFuture.channel().closeFuture().sync();
		} finally {
			//优雅关闭
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
