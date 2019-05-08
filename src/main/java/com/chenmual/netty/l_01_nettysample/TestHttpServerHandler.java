package com.chenmual.netty.l_01_nettysample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * 通道处理器
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

	//读取客户端发过来的请求 并向客户端返回响应
	//重写的这个方法在5.0之后被重命名为MessageReceived
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

		System.out.println(msg.getClass());//curl可能会多请求叫DefaultHttpRequest的request
		System.out.println(ctx.channel().remoteAddress());//客户端那边开启的地址:端口号
		TimeUnit.SECONDS.sleep(8);//此时查看端口对应情况netstat -ano | findstr 8899 或者lsof -i:8899查看对应情况

		if(msg instanceof HttpRequest){
			System.out.println("request");

			HttpRequest httpRequest = (HttpRequest)msg;

			System.out.println("请求方法名:" + httpRequest.method().name());
			URI uri = new URI(httpRequest.uri());
			if("/favicon.ico".equals(uri.getPath())){
				//部分浏览器会额外发送一个请求标签图标(浏览器上标签页显示一个图标)的请求
				//如果是这个请求则不继续执行
				System.out.println("请求favicon.ico");
				return;
			}


			//构造字符串的bytebuf对象
			ByteBuf content = Unpooled.copiedBuffer("hello world",CharsetUtil.UTF_8);

			//构造支持响应的对象
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,//基于1.1的协议链接会保持3秒
					HttpResponseStatus.OK, content);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

			ctx.writeAndFlush(response);

			ctx.channel().close();

		}
	}


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive");
		super.channelActive(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelRegistered");
		super.channelRegistered(ctx);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerAdded");
		super.handlerAdded(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive");
		super.channelInactive(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelUnregistered");
		super.channelUnregistered(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerRemoved");
		//链接失去了
	}
}
